package com.sleepwell.userapi.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class AccommodationSearchDto {

    private final String accommodationName;

    private final String accommodationType;

    private final String streetAddress;

    private final String detailAddress;

    private final String postcode;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkInDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkOutDate;

    private final Integer minPrice;

    private final Integer maxPrice;

    private final Integer numberOfGuest;

}
