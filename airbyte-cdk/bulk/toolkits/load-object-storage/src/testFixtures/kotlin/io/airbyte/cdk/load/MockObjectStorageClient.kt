/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.load

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import io.airbyte.cdk.load.file.object_storage.ObjectStorageClient
import io.airbyte.cdk.load.file.object_storage.RemoteObject
import io.airbyte.cdk.load.file.object_storage.StreamingUpload
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import java.io.InputStream
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.flow.flow

private val logger = KotlinLogging.logger {}

class MockRemoteObject(
    override val key: String,
    override val storageConfig: Int,
    val data: ByteArray,
    val metadata: Map<String, String> = emptyMap()
) : RemoteObject<Int>

class MockObjectStreamingUpload(
    private val client: MockObjectStorageClient,
    private val key: String,
    private val metadata: Map<String, String>,
) : StreamingUpload<MockRemoteObject> {
    private val parts: MutableList<Pair<Int, ByteArray>> = mutableListOf()
    override suspend fun uploadPart(part: ByteArray, index: Int) {
        parts.add(index to part)
        updateRemoteStorage()
    }

    override suspend fun complete(): MockRemoteObject {
        updateRemoteStorage()
        return client.get(key).also {
            logger.info { "Writing $key to MockObjectStorage\n${it.data.decodeToString()}" }
        }
    }

    private suspend fun updateRemoteStorage() {
        parts.sortBy { it.first }
        val data = parts.map { it.second }.reduce { acc, bytes -> acc + bytes }
        client.put(key, data)
    }
}

@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION", justification = "Kotlin async continuation")
@Singleton
@Requires(env = ["MockObjectStorageClient"])
class MockObjectStorageClient : ObjectStorageClient<MockRemoteObject> {
    private val objects = ConcurrentHashMap<String, MockRemoteObject>()

    override suspend fun list(prefix: String) = flow {
        objects.values.filter { it.key.startsWith(prefix) }.forEach { emit(it) }
    }

    override suspend fun move(remoteObject: MockRemoteObject, toKey: String): MockRemoteObject {
        val oldObject =
            objects.remove(remoteObject.key) ?: throw IllegalArgumentException("Object not found")
        val newObject = MockRemoteObject(toKey, oldObject.storageConfig, oldObject.data)
        objects[toKey] = newObject
        return newObject
    }

    override suspend fun move(key: String, toKey: String): MockRemoteObject {
        val remoteObject = objects[key] ?: throw IllegalArgumentException("Object not found")
        return move(remoteObject, toKey)
    }

    override suspend fun <R> get(key: String, block: (InputStream) -> R): R {
        val remoteObject = objects[key] ?: throw IllegalArgumentException("Object not found")
        return block(remoteObject.data.inputStream())
    }

    fun get(key: String): MockRemoteObject =
        objects[key] ?: throw IllegalArgumentException("Object $key not found")

    override suspend fun getMetadata(key: String): Map<String, String> {
        return objects[key]?.metadata ?: emptyMap()
    }

    override suspend fun put(key: String, bytes: ByteArray): MockRemoteObject {
        val remoteObject = MockRemoteObject(key, 0, bytes)
        objects[key] = remoteObject
        return remoteObject
    }

    override suspend fun delete(key: String) {
        objects.remove(key)
    }

    override suspend fun delete(keys: Set<String>) {
        keys.forEach { key -> delete(key = key) }
    }

    override suspend fun delete(remoteObject: MockRemoteObject) {
        objects.remove(remoteObject.key)
    }

    override suspend fun startStreamingUpload(
        key: String,
        metadata: Map<String, String>
    ): StreamingUpload<MockRemoteObject> = MockObjectStreamingUpload(this, key, metadata)
}
