package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationDetailInfoDto {

    private final Long accommodationId;

    private final String accommodationName;

    private final int price;

    private final String accommodationType;

    private final String location;

    private final String checkInDate;

    private final String checkOutDate;

    private final String checkInTime;

    private final String checkOutTime;

    private final Integer maximumNumberOfGuest;

    private final String description;

}
