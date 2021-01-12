package org.geekbang.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = {"topic1"})
    public void onMessage(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

}
