/*
 *
 */

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.ImmutableMap;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndMetadata;

/**
 * Created by xinxin on 6/14/14.
 */
public class TestKafkaMetadata
{
    @Test
    public void testKafka()
    {
        SimpleConsumer simpleConsumer = new SimpleConsumer("kafka-broker-lb.snc1", 2181, 100, 100, "abc");
        FetchRequest fetchRequest = new FetchRequestBuilder().addFetch("mobile_notifications", 0, 0, 100).build();
        FetchResponse fetchResponse = simpleConsumer.fetch(fetchRequest);
    }

    @Test
    public void testHighLevelKafkaApi() {
        ConsumerConnector consumerConnector =
                kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig("localhost:2181", "test_kafka"));

        Map<String, Integer> topicCountMap = ImmutableMap.of("mobile_notifications", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);

        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get("mobile_notifications");


        for(KafkaStream<byte[], byte[]> stream: streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();

            while(it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> messageAndMetadata = it.next();
                System.out.println("value is: " + new String(messageAndMetadata.message()));
            }
        }


    }





    @Test
    public void testZookeeper() throws Exception
    {
        Watcher watcher = new Watcher()
        {
            @Override
            public void process(WatchedEvent event)
            {
                System.out.println(event);
            }
        };

        ZooKeeper zookeeper = new ZooKeeper("kafka-broker-lb.snc1:2181", 2000, watcher);
        System.out.println(zookeeper.getChildren("/brokers/topics", false));
    }

    private static ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");

        return new ConsumerConfig(props);
    }

}


