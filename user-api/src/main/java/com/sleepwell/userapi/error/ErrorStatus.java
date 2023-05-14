package com.sleepwell.userapi.error;

import lombok.Getter;

@Getter
public enum ErrorStatus {

    // 회원 관련 에러
    DUPLICATED_EMAIL_FOUND(401, "중복된 이메일입니다"),

    // 로그인 관련 에러
    AUTHENTICATION_FAIL(401, "로그인에 실패하였습니다. 이메일 혹은 비밀번호를 확인하세요."),

    // 객실 관련 에러
    ROOM_NOT_FOUND(404, "해당 객실이 존재하지 않습니다."),

    // 예약 관련 에러
    INVALID_RESERVATION_DATE(400, "해당 일자에 예약이 불가합니다."),
    INVALID_NUMBER_OF_GUEST(400, "숙박 가능 인원을 초과하였습니다."),
    RESERVATION_NOT_FOUND(404, "해당 예약 정보를 조회할 수 없습니다."),

    // 결제 관련 에러
    PAYMENT_NOT_COMPLETE(400, "결제가 완료되지 않았습니다."),
    PAYMENT_NOT_FOUND(400, "유효하지 않은 결제 ID 입니다."),
    PAYMENT_AMOUNT_MISMATCH(400, "결제 금액이 일치하지 않습니다."),

    // 예기치 못한 경우
    UNEXPECTED_EXCEPTION(400, "요청을 처리할 수 없습니다.");

    private final int statusCode;
    private final String message;

    ErrorStatus(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
