package com.sleepwell.userapi.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {

    private String message;

    private LogLevel logLevel;

}
