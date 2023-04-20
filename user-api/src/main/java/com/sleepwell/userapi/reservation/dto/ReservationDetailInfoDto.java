package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationDetailInfoDto {

    private final Long reservationId;

    private final Long accommodationId;

    private final String accommodationName;

    private final String hostName;

    private final String guestName;

    private final int price;

    private final String paymentType;

    private final String accommodationType;

    private final String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkOutDate;

    private final String checkInTime;

    private final String checkOutTime;

    private final int guests;

    private final String reservationStatus;
}
