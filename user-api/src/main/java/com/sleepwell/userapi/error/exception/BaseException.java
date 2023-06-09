package com.sleepwell.userapi.error.exception;

import com.sleepwell.userapi.error.ErrorStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final int statusCode;

    public BaseException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.statusCode = errorStatus.getStatusCode();
    }
}
