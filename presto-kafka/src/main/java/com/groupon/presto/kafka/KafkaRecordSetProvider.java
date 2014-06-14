/*
 *
 */
package com.groupon.presto.kafka;

import java.util.List;

import com.facebook.presto.spi.ConnectorColumnHandle;
import com.facebook.presto.spi.ConnectorRecordSetProvider;
import com.facebook.presto.spi.ConnectorSplit;
import com.facebook.presto.spi.RecordSet;

/**
 * Created by xinxin on 6/11/14.
 */
public class KafkaRecordSetProvider implements ConnectorRecordSetProvider
{
    @Override
    public RecordSet getRecordSet(ConnectorSplit split, List<? extends ConnectorColumnHandle> columns)
    {
        return null;
    }
}
