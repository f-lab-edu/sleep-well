package com.sleepwell.kafka.log;

import com.sleepwell.common.message.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogConsumer {

    @KafkaListener(
            topics = "log-topic",
            groupId = "log-message-consumer",
            containerFactory = "logMessageConcurrentKafkaListenerContainerFactory"
    )
    public void listen(@Payload LogMessage logMessage) {
        if (logMessage.getLogLevel() == LogLevel.INFO) {
            log.info(logMessage.getMessage());
        } else if (logMessage.getLogLevel() == LogLevel.TRACE) {
            log.trace(logMessage.getMessage());
        } else if (logMessage.getLogLevel() == LogLevel.DEBUG) {
            log.debug(logMessage.getMessage());
        } else if (logMessage.getLogLevel() == LogLevel.WARN) {
            log.warn(logMessage.getMessage());
        } else {
            log.error(logMessage.getMessage());
        }
    }
}
