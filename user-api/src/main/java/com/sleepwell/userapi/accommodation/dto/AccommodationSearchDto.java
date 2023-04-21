package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AccommodationSearchDto {

    private final String accommodationName;

    private final String accommodationType;

    private final String location;

    private final LocalDate checkInDate;

    private final LocalDate checkOutDate;

    private final Integer minPrice;

    private final Integer maxPrice;

    private final Integer numberOfGuest;

}
