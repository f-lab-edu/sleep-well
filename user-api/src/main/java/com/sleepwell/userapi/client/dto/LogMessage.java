package com.sleepwell.userapi.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

import java.io.PrintWriter;
import java.io.StringWriter;

//TODO: 공통 모듈에서 메시지 객체를 관리하도록 변경 예정
@Getter
@NoArgsConstructor
public class LogMessage {

    private String message;

    private LogLevel logLevel;

    public LogMessage(Exception exception, LogLevel logLevel) {
        this.message = convertExceptionToString(exception);
        this.logLevel = logLevel;
    }

    public LogMessage(String message, LogLevel logLevel) {
        this.message = message;
        this.logLevel = logLevel;
    }

    private String convertExceptionToString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
