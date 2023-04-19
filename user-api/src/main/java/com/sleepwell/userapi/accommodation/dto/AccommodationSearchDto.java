package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationSearchDto {

    private String accommodationName;

    private String accommodationType;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private int minPrice;

    private int maxPrice;

    private int guests;
}
