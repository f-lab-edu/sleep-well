package com.sleepwell.userapi.error.handler;

import com.sleepwell.userapi.error.ErrorStatus;
import com.sleepwell.userapi.error.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> baseExceptionHandle(BaseException e) {
        log.error("{} - {}", e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unexpectedExceptionHandle(Exception e) {
        log.error("{} - {}", e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(ErrorStatus.UNEXPECTED_EXCEPTION.getStatusCode()).body(ErrorStatus.UNEXPECTED_EXCEPTION.getMessage());
    }
}
