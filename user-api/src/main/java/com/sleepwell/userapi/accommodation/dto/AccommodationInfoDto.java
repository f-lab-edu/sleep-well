package com.sleepwell.userapi.accommodation.dto;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
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


    public static AccommodationInfoDto toDto(Accommodation accommodation) {
        return AccommodationInfoDto.builder()
                .accommodationName(accommodation.getAccommodationName())
                .accommodationType(accommodation.getAccommodationType())
                .price(accommodation.getPrice())
                .location(accommodation.getLocation())
                .maximumNumberOfGuest(accommodation.getMaximumNumberOfGuest())
                .build();
    }
}
