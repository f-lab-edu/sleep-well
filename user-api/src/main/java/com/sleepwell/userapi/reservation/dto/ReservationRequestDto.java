package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationRequestDto {

    private Long accommodationId;

    private Long guestId;

    private String accommodationName;

    private String paymentType;

    private String accommodationType;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private String guests;
}
