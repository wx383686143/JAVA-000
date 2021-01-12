package org.geekbang.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaInitConfig {

    @Bean
    public NewTopic initTopic() {
        // 创建一个topic，设置分区为8，分区副本为2
        return new NewTopic("kafka.topic", 8, (short) 2);
    }

    @Bean
    public NewTopic updateTopic() {
        // 如果修改分区数，只需修改配置重启项目即可
        // 修改分区输并不会导致数据的丢失，但是分区数只能增大不能减小
        return new NewTopic("kafka.topic", 10, (short) 2);
    }

}
