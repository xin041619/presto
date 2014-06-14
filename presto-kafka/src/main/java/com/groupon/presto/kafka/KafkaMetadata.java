/*
 *
 */
package com.groupon.presto.kafka;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.facebook.presto.spi.ColumnMetadata;
import com.facebook.presto.spi.ConnectorColumnHandle;
import com.facebook.presto.spi.ConnectorMetadata;
import com.facebook.presto.spi.ConnectorOutputTableHandle;
import com.facebook.presto.spi.ConnectorSession;
import com.facebook.presto.spi.ConnectorTableHandle;
import com.facebook.presto.spi.ConnectorTableMetadata;
import com.facebook.presto.spi.SchemaTableName;
import com.facebook.presto.spi.SchemaTablePrefix;
import com.google.common.base.Throwables;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by xinxin on 6/11/14.
 */
public class KafkaMetadata implements ConnectorMetadata
{
    private final ZooKeeper zooKeeper;

    public KafkaMetadata() throws IOException
    {
        this.zooKeeper = new ZooKeeper("kafka-broker-lb.snc1:2181", 2000, new Watcher()
        {
            @Override
            public void process(WatchedEvent event)
            {}
        });

    }

    @Override
    public List<String> listSchemaNames(ConnectorSession session)
    {
        try {
            return zooKeeper.getChildren("/brokers/topics", false);
        }
        catch (InterruptedException | KeeperException e) {
            Throwables.propagate(e);
            return null;
        }
    }

    @Override
    public ConnectorTableHandle getTableHandle(ConnectorSession session, SchemaTableName tableName)
    {
        return null;
    }

    @Override
    public ConnectorTableMetadata getTableMetadata(ConnectorTableHandle table)
    {
        return null;
    }

    @Override
    public List<SchemaTableName> listTables(ConnectorSession session, String schemaNameOrNull)
    {
        return null;
    }

    @Override
    public ConnectorColumnHandle getColumnHandle(ConnectorTableHandle tableHandle, String columnName)
    {
        return null;
    }

    @Override
    public ConnectorColumnHandle getSampleWeightColumnHandle(ConnectorTableHandle tableHandle)
    {
        return null;
    }

    @Override
    public boolean canCreateSampledTables(ConnectorSession session)
    {
        return false;
    }

    @Override
    public Map<String, ConnectorColumnHandle> getColumnHandles(ConnectorTableHandle tableHandle)
    {
        return null;
    }

    @Override
    public ColumnMetadata getColumnMetadata(ConnectorTableHandle tableHandle, ConnectorColumnHandle columnHandle)
    {
        return null;
    }

    @Override
    public Map<SchemaTableName, List<ColumnMetadata>> listTableColumns(ConnectorSession session, SchemaTablePrefix prefix)
    {
        return null;
    }

    @Override
    public ConnectorTableHandle createTable(ConnectorSession session, ConnectorTableMetadata tableMetadata)
    {
        throw new UnsupportedOperationException("Table creation is not allowed");
    }

    @Override
    public void dropTable(ConnectorTableHandle tableHandle)
    {
        throw new UnsupportedOperationException("Table dropping is not allowed");
    }

    @Override
    public ConnectorOutputTableHandle beginCreateTable(ConnectorSession session, ConnectorTableMetadata tableMetadata)
    {
        throw new UnsupportedOperationException("Table creation is not allowed");
    }

    @Override
    public void commitCreateTable(ConnectorOutputTableHandle tableHandle, Collection<String> fragments)
    {
        throw new UnsupportedOperationException("Table creation is not allowed");
    }
}
