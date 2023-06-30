package com.sleepwell.userapi.error.handler;

import com.sleepwell.userapi.client.LogFeignClient;
import com.sleepwell.userapi.client.dto.LogMessage;
import com.sleepwell.userapi.error.ErrorStatus;
import com.sleepwell.userapi.error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private final LogFeignClient logFeignClient;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> baseExceptionHandle(BaseException e) {
        logFeignClient.printLogMessage(new LogMessage(e, LogLevel.DEBUG));
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unexpectedExceptionHandle(Exception e) {
        logFeignClient.printLogMessage(new LogMessage(e, LogLevel.ERROR));
        return ResponseEntity.status(ErrorStatus.UNEXPECTED_EXCEPTION.getStatusCode()).body(ErrorStatus.UNEXPECTED_EXCEPTION.getMessage());
    }
}
