package com.sleepwell.userapi.log;

import com.sleepwell.common.message.LogMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogProducer {

    private final KafkaTemplate<String, LogMessage> logMessageKafkaTemplate;

    private static final String TOPIC_NAME = "log-topic";

    public void send(LogMessage message) {
        logMessageKafkaTemplate.send(TOPIC_NAME, message);
    }
}
