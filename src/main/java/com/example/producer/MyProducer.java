package com.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @Description: 生产者
 * @Auther: shanpeng.wang
 * @Create: 2020/12/4 10:11
 */
public class MyProducer {
    public static void main(String[] args) {
        //1.创建Kafka生产者的配置信息
        Properties properties = new Properties();
        //指定连接的kafka集群
        properties.put("bootstrap.servers","172.31.2.101:9092,172.31.2.102:9092,172.31.2.103:9092");
        //ACK应答级别
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //重复次数
        properties.put("retries",3);
        //批次大小  16k
        properties.put("batch.size",16384);
        //等待时间
        properties.put("linger.ms",1);
        //RecordAccumulator缓冲区大小 32M
        properties.put("buffer.memory",33554432);
        //key，value的序列化类
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //发送数据
        for(int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<String,String>("first","hello--"+i));
        }

        //关闭资源
        producer.close();
    }
}
