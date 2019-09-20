package chapter1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 代码清单1-1
 * Created by 朱小厮 on 2018/7/21.
 */
public class ProducerFastStart {
    public static final String brokerList = "localhost:19092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);


        KafkaProducer<String, String> producer =
                new KafkaProducer<>(properties);
        try {
            for(int i = 0; i < 100; i++){
                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topic, "hello, Kafka! " + (i+1));
                producer.send(record);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
