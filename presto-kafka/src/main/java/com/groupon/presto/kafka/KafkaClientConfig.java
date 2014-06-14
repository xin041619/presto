/*
 *
 */
package com.groupon.presto.kafka;

import io.airlift.configuration.Config;

/**
 * Created by xinxin on 6/10/14.
 */
public class KafkaClientConfig
{
    private int testConfigNumber = 1;

    public int getTestConfigNumber()
    {
        return testConfigNumber;
    }

    @Config("kafka.test-config-number")
    public void setTestConfigNumber(int testConfigNumber)
    {
        this.testConfigNumber = testConfigNumber;
    }
}
