package com.sleepwell.kafka.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {

    private String message;

    private LogLevel logLevel;

    public static String convertExceptionToString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
