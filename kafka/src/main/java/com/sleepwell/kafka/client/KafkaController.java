package com.sleepwell.kafka.client;

import com.sleepwell.common.message.LogMessage;
import com.sleepwell.kafka.log.LogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class KafkaController {

    private final LogProducer logProducer;

    @PostMapping
    public ResponseEntity<Void> printLogMessage(@RequestBody LogMessage logMessage) {
        logProducer.send(logMessage);
        return ResponseEntity.ok().build();
    }
}
