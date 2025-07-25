/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.read

import io.airbyte.cdk.jdbc.JDBC_PROPERTY_PREFIX
import io.micronaut.context.annotation.Requires
import io.micronaut.context.annotation.Secondary
import jakarta.inject.Singleton

/** Base class for JDBC implementations of [PartitionsCreatorFactory]. */
abstract class JdbcPartitionsCreatorFactory<
    A : JdbcSharedState,
    S : JdbcStreamState<A>,
    P : JdbcPartition<S>,
>(
    val partitionFactory: JdbcPartitionFactory<A, S, P>,
) : PartitionsCreatorFactory {

    override fun make(feedBootstrap: FeedBootstrap<*>): PartitionsCreator? {
        if (feedBootstrap !is StreamFeedBootstrap) return null
        val partition: P = partitionFactory.create(feedBootstrap) ?: return CreateNoPartitions
        return partitionsCreator(partition)
    }

    abstract fun partitionsCreator(partition: P): JdbcPartitionsCreator<A, S, P>
}

/** Sequential JDBC implementation of [PartitionsCreatorFactory]. */
@Singleton
@Secondary
@Requires(property = MODE_PROPERTY, value = "sequential")
class JdbcSequentialPartitionsCreatorFactory<
    A : JdbcSharedState,
    S : JdbcStreamState<A>,
    P : JdbcPartition<S>,
>(
    partitionFactory: JdbcPartitionFactory<A, S, P>,
) : JdbcPartitionsCreatorFactory<A, S, P>(partitionFactory) {

    override fun partitionsCreator(partition: P): JdbcPartitionsCreator<A, S, P> =
        JdbcSequentialPartitionsCreator(partition, partitionFactory)
}

/** Concurrent JDBC implementation of [PartitionsCreatorFactory]. */
@Singleton
@Secondary
@Requires(property = MODE_PROPERTY, value = "concurrent")
class JdbcConcurrentPartitionsCreatorFactory<
    A : JdbcSharedState,
    S : JdbcStreamState<A>,
    P : JdbcPartition<S>,
>(
    partitionFactory: JdbcPartitionFactory<A, S, P>,
) : JdbcPartitionsCreatorFactory<A, S, P>(partitionFactory) {

    override fun partitionsCreator(partition: P): JdbcPartitionsCreator<A, S, P> =
        JdbcConcurrentPartitionsCreator(partition, partitionFactory)
}

@Singleton
// TODO: For now, trigger-based CDC connectors will use mode=concurrent_with_cdc and
//  provide their own supplier. In the future, the CDK will directly support this execution mode.
@Requires(property = MODE_PROPERTY, pattern = "^(sequential|concurrent)$")
class JdbcPartitionCreatorFactorySupplier<
    T : JdbcPartitionsCreatorFactory<A, S, P>,
    A : JdbcSharedState,
    S : JdbcStreamState<A>,
    P : JdbcPartition<S>
>(val factory: T) : PartitionsCreatorFactorySupplier<T> {
    override fun get(): T = factory
}

const val MODE_PROPERTY = "${JDBC_PROPERTY_PREFIX}.mode"
