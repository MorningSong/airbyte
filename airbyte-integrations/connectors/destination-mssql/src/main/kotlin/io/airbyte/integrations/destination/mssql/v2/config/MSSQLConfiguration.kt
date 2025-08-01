/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.mssql.v2.config

import io.airbyte.cdk.ConfigErrorException
import io.airbyte.cdk.command.FeatureFlag
import io.airbyte.cdk.load.command.DestinationConfiguration
import io.airbyte.cdk.load.command.DestinationConfigurationFactory
import io.airbyte.cdk.load.command.object_storage.ObjectStorageUploadConfiguration
import io.airbyte.cdk.load.file.azureBlobStorage.GENERATION_ID_METADATA_KEY_OVERRIDE
import io.airbyte.cdk.ssh.SshTunnelMethodConfiguration
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

data class MSSQLConfiguration(
    val host: String,
    val port: Int,
    val database: String,
    val schema: String,
    val user: String?,
    val password: String?,
    val jdbcUrlParams: String?,
    val sslMethod: EncryptionMethod,
    val ssh: SshTunnelMethodConfiguration?,
    override val mssqlLoadTypeConfiguration: MSSQLLoadTypeConfiguration,
) : DestinationConfiguration(), MSSQLLoadTypeConfigurationProvider {
    override val recordBatchSizeBytes = ObjectStorageUploadConfiguration.DEFAULT_PART_SIZE_BYTES

    val numInputPartitions: Int = 1 // this should not be raised without implementing a partitioner
    val batchEveryNRecords: Int = 5_000
    val maxBatchSizeBytes: Long = recordBatchSizeBytes
    val maxNumOpenLoaders: Int = 8 // allows for 1 concurrent open and close + 8 concurrent keys

    /**
     * Azure requires blob metadata keys to be alphanumeric+underscores, so replace the dashes with
     * underscores.
     */
    override val generationIdMetadataKey: String
        get() = GENERATION_ID_METADATA_KEY_OVERRIDE
}

@Singleton
class MSSQLConfigurationFactory(private val featureFlags: Set<FeatureFlag>) :
    DestinationConfigurationFactory<MSSQLSpecification, MSSQLConfiguration> {

    constructor() : this(emptySet())

    override fun makeWithoutExceptionHandling(pojo: MSSQLSpecification): MSSQLConfiguration {
        if (
            pojo.sslMethod is Unencrypted &&
                featureFlags.contains(FeatureFlag.AIRBYTE_CLOUD_DEPLOYMENT)
        ) {
            throw ConfigErrorException("Connection from Airbyte Cloud requires SSL encryption")
        }
        return makeWithOverrides(spec = pojo)
    }

    fun makeWithOverrides(
        spec: MSSQLSpecification,
        overrides: Map<String, String> = emptyMap()
    ): MSSQLConfiguration {
        return MSSQLConfiguration(
            host = overrides.getOrDefault("host", spec.host),
            port = overrides.getOrDefault("port", spec.port.toString()).toInt(),
            database = overrides.getOrDefault("database", spec.database),
            schema = overrides.getOrDefault("schema", spec.schema),
            user = overrides.getOrDefault("user", spec.user),
            password = overrides.getOrDefault("password", spec.password),
            jdbcUrlParams = overrides.getOrDefault("jdbcUrlParams", spec.jdbcUrlParams),
            sslMethod = spec.sslMethod,
            mssqlLoadTypeConfiguration = spec.toLoadConfiguration(),
            ssh = spec.tunnelMethodJson
        )
    }
}

@Factory
class MSSQLConfigurationProvider(private val config: DestinationConfiguration) {
    @Singleton
    fun get(): MSSQLConfiguration {
        return config as MSSQLConfiguration
    }
}
