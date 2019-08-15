package com.example.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by xulei on 2019/8/6.
 */
@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用“，”隔开
    @KafkaListener(topics = {"testTopic"},groupId = "testGroup")
    public void receive(ConsumerRecord<?, ?> record){
        logger.info("receive消费得到的消息---key: " + record.key());
        logger.info("receive消费得到的消息---value: " + record.value().toString());
    }
    @KafkaListener(topics = {"testTopic"},groupId = "testGroup")
    public void receive1(ConsumerRecord<?, ?> record){
        logger.info("receive1消费得到的消息---key: " + record.key());
        logger.info("receive1消费得到的消息---value: " + record.value().toString());
    }
    @KafkaListener(topics = {"testTopic"},groupId = "testGroup")
    public void receive2(ConsumerRecord<?, ?> record){
        logger.info("receive2消费得到的消息---key: " + record.key());
        logger.info("receive2消费得到的消息---value: " + record.value().toString());
    }
    @KafkaListener(topics = {"testTopic"},groupId = "testGroup")
    public void receive3(ConsumerRecord<?, ?> record){
        logger.info("receive3消费得到的消息---key: " + record.key());
        logger.info("receive3消费得到的消息---value: " + record.value().toString());
    }
}
