/*
 *
 */
package com.groupon.presto.kafka;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.lang.management.ManagementFactory;
import java.util.Map;

import javax.management.MBeanServer;

import com.facebook.presto.spi.Connector;
import com.facebook.presto.spi.ConnectorFactory;
import com.google.common.base.Throwables;
import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Module;

import org.weakref.jmx.guice.MBeanModule;

import io.airlift.bootstrap.Bootstrap;
import io.airlift.json.JsonModule;
import io.airlift.node.NodeModule;

/**
 * Created by xinxin on 6/9/14.
 */
public class KafkaConnectorFactory implements ConnectorFactory
{
    private final String name;
    private final Map<String, String> optionalConfig;

    public KafkaConnectorFactory(String name, Map<String, String> optionalConfig)
    {
        checkArgument(!isNullOrEmpty(name), "name is null or empty");
        this.name = name;
        this.optionalConfig = checkNotNull(optionalConfig, "optionalConfig is null");
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Connector create(String connectorId, Map<String, String> config)
    {
        checkNotNull(config, "config is null");

        try {
            Bootstrap app = new Bootstrap(
                    new NodeModule(),
                    new MBeanModule(),
                    new JsonModule(),
                    new KafkaClientModule(),
                    new Module()
                    {
                        @Override
                        public void configure(Binder binder)
                        {
                            MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
                            binder.bind(MBeanServer.class).toInstance(new KafkaRebindSafeMBeanServer(platformMBeanServer));
                        }

                    }
            );

            Injector injector = app.strictConfig().doNotInitializeLogging()
                    .setRequiredConfigurationProperties(config)
                    .setOptionalConfigurationProperties(optionalConfig).initialize();

            return injector.getInstance(KafkaConnector.class);
        }
        catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
