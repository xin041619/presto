/*
 *
 */
package com.groupon.presto.kafka;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.facebook.presto.spi.ConnectorFactory;
import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Created by xinxin on 6/9/14.
 */
public class KafkaPlugin implements Plugin
{
    private Map<String, String> optionalConfig = ImmutableMap.of();

    @Override
    public void setOptionalConfig(Map<String, String> optionalConfig)
    {
        this.optionalConfig = ImmutableMap.copyOf(checkNotNull(optionalConfig, "optionalConfig is null"));
    }

    @Override
    public <T> List<T> getServices(Class<T> type)
    {
        if (type == ConnectorFactory.class) {
            return ImmutableList.of(type.cast(new KafkaConnectorFactory("kafka", optionalConfig)));
        }
        return ImmutableList.of();
    }
}
