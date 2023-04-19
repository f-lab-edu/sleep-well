package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationRequestDto {

    private final Long accommodationId;

    private final Long guestId;

    private final String accommodationName;

    private final String paymentType;

    private final String accommodationType;

    private final String location;

    private final String checkInDate;

    private final String checkOutDate;

    private final String guests;
}
