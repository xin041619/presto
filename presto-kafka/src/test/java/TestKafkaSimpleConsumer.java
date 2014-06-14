import java.util.Collections;
import java.util.List;

import org.junit.Test;

import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.consumer.SimpleConsumer;

/**
 * Created by xinxin on 6/16/14.
 */
public class TestKafkaSimpleConsumer
{


    @Test
    public void testLowLevelKafkaApi() {
//        SimpleConsumer simpleConsumer = Consumer.

    }

    PartitionMetadata findLeader(List<String> brokers, int port, String topic, int partition) {
        PartitionMetadata returnMetadata = null;

        List<String> topics = Collections.singletonList(topic);

        for(String broker: brokers) {
            SimpleConsumer simpleConsumer = new SimpleConsumer(broker, port, 100000, 64*2014, "test_kafka");
//            TopicMetadataRequest topicMetadataRequest  = new TopicMetadataRequest(topics, 2);
        }
        return null;

    }

}
