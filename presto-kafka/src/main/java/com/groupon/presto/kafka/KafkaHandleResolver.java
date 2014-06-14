/*
 *
 */
package com.groupon.presto.kafka;

import com.facebook.presto.spi.ConnectorColumnHandle;
import com.facebook.presto.spi.ConnectorHandleResolver;
import com.facebook.presto.spi.ConnectorIndexHandle;
import com.facebook.presto.spi.ConnectorSplit;
import com.facebook.presto.spi.ConnectorTableHandle;

/**
 * Created by xinxin on 6/11/14.
 */
public class KafkaHandleResolver
        implements ConnectorHandleResolver
{
    @Override
    public boolean canHandle(ConnectorTableHandle tableHandle)
    {
        return false;
    }

    @Override
    public boolean canHandle(ConnectorColumnHandle columnHandle)
    {
        return false;
    }

    @Override
    public boolean canHandle(ConnectorSplit split)
    {
        return false;
    }

    @Override
    public boolean canHandle(ConnectorIndexHandle indexHandle)
    {
        return false;
    }

    @Override
    public Class<? extends ConnectorTableHandle> getTableHandleClass()
    {
        return null;
    }

    @Override
    public Class<? extends ConnectorColumnHandle> getColumnHandleClass()
    {
        return null;
    }

    @Override
    public Class<? extends ConnectorIndexHandle> getIndexHandleClass()
    {
        return null;
    }

    @Override
    public Class<? extends ConnectorSplit> getSplitClass()
    {
        return null;
    }
}
