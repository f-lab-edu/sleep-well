package com.sleepwell.userapi.client;

import com.sleepwell.common.message.LogMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "logClient", url = "localhost:9001/log")
public interface LogFeignClient {

    @PostMapping(consumes = "application/json")
    ResponseEntity<Void> printLogMessage(LogMessage logMessage);
}
