/*
 *
 */
package com.groupon.presto.kafka;

import static io.airlift.configuration.ConfigurationModule.bindConfig;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

/**
 * Created by xinxin on 6/10/14.
 */
public class KafkaClientModule implements Module
{
    @Override
    public void configure(Binder binder)
    {
        binder.bind(KafkaConnector.class).in(Scopes.SINGLETON);

        binder.bind(KafkaMetadata.class).in(Scopes.SINGLETON);
        binder.bind(KafkaSplitManager.class).in(Scopes.SINGLETON);
        binder.bind(KafkaRecordSetProvider.class).in(Scopes.SINGLETON);
        binder.bind(KafkaHandleResolver.class).in(Scopes.SINGLETON);

        bindConfig(binder).to(KafkaClientConfig.class);
    }
}
