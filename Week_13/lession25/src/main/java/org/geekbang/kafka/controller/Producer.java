package org.geekbang.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("kafka/{msg}")
    public void sendMsg(@PathVariable("msg") String msg) {
        kafkaTemplate.send("topic1", msg);
    }


}
