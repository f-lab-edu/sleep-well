package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class AccommodationDetailInfoDto {

    private final Long accommodationId;

    private final String accommodationName;

    private final int price;

    private final String accommodationType;

    private final String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkOutDate;

    @DateTimeFormat(pattern = "hh:mm")
    private final LocalTime checkInTime;

    @DateTimeFormat(pattern = "hh:mm")
    private final LocalTime checkOutTime;

    private final Integer maximumNumberOfGuest;

    private final String description;

}
