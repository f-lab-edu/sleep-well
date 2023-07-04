package com.sleepwell.userapi.config;

import com.sleepwell.common.message.LogMessage;
import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Setter
@Configuration
@ConfigurationProperties("kafka.broker")
public class KafkaProducerConfig {

    private String port;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, port);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    //kafka producer config
    @Bean
    public ProducerFactory<String, LogMessage> logMessageProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    //kafka template config
    @Bean
    public KafkaTemplate<String, LogMessage> reservationMessageKafkaTemplate() {
        return new KafkaTemplate<>(logMessageProducerFactory());
    }
}
