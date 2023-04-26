package com.sleepwell.userapi.accommodation.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class AccommodationSearchDto {

    private String accommodationName;

    private String accommodationType;

    private String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOutDate;

    private Integer minPrice;

    private Integer maxPrice;

    private Integer numberOfGuest;

}
