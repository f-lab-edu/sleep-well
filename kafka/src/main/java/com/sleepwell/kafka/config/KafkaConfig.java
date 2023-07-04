package com.sleepwell.kafka.config;

import lombok.Setter;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Setter
@EnableKafka
@Configuration
@ConfigurationProperties("kafka.broker")
public class KafkaConfig {

    private String port;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, port);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic logTopic() {
        return TopicBuilder
                .name("log-topic")
                .build();
    }
}
