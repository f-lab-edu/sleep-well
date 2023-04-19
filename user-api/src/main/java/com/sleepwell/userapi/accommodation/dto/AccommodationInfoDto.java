package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationInfoDto {

    private final String accommodationName;

    private final String accommodationType;

    private final int price;

    private final String location;

    private final int maximumNumberOfGuest;

}
