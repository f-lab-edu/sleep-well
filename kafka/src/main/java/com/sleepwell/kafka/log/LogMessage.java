package com.sleepwell.kafka.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.logging.LogLevel;

//TODO: 공통 모듈에서 메시지 객체를 관리하도록 변경 예정
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {

    private String message;

    private LogLevel logLevel;
}
