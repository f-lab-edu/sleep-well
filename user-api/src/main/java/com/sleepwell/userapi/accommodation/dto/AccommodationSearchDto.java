package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationSearchDto {

    private final String accommodationName;

    private final String accommodationType;

    private final String location;

    private final String checkInDate;

    private final String checkOutDate;

    private final int minPrice;

    private final int maxPrice;

    private final int guests;

}
