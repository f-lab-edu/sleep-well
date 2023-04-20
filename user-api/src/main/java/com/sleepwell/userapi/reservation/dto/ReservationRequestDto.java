package com.sleepwell.userapi.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationRequestDto {

    private final Long accommodationId;

    private final Long guestId;

    private final String accommodationName;

    private final String paymentType;

    private final String accommodationType;

    private final String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    private final LocalDate checkInDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    private final LocalDate checkOutDate;

    private final int numberOfGuest;

    public Reservation toEntity() {
        return Reservation.builder()
                .paymentType(paymentType)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .numberOfGuest(numberOfGuest)
                .build();
    }
}
