package com.sleepwell.userapi.client;

import com.sleepwell.userapi.client.dto.LogMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "logClient", url = "localhost:9001/log")
public interface LogFeignClient {

    @PostMapping(consumes = "application/json")
    CompletableFuture<Void> printLogMessage(LogMessage logMessage);
}
