package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;

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

    private final String checkInDate;

    private final String checkOutDate;

    private final String checkInTime;

    private final String checkOutTime;

    private final int guests;

    private final String reservationStatus;
}
