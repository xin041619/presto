/*
 *
 */
package com.groupon.presto.kafka;

import java.util.List;

import com.facebook.presto.spi.ConnectorColumnHandle;
import com.facebook.presto.spi.ConnectorPartition;
import com.facebook.presto.spi.ConnectorPartitionResult;
import com.facebook.presto.spi.ConnectorSplitManager;
import com.facebook.presto.spi.ConnectorSplitSource;
import com.facebook.presto.spi.ConnectorTableHandle;
import com.facebook.presto.spi.TupleDomain;

/**
 * Created by xinxin on 6/11/14.
 */
public class KafkaSplitManager implements ConnectorSplitManager
{
    @Override
    public String getConnectorId()
    {
        return null;
    }

    @Override
    public ConnectorPartitionResult getPartitions(ConnectorTableHandle table, TupleDomain<ConnectorColumnHandle> tupleDomain)
    {
        return null;
    }

    @Override
    public ConnectorSplitSource getPartitionSplits(ConnectorTableHandle table, List<ConnectorPartition> partitions)
    {
        return null;
    }
}
