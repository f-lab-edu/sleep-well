package com.sleepwell.userapi.accommodation.dto;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.entity.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationInfoDto {

    private final String accommodationName;

    private final String accommodationType;

    private final int price;

    private final Address address;

    private final int maximumNumberOfGuest;


    public static AccommodationInfoDto fromEntity(Accommodation accommodation) {
        return AccommodationInfoDto.builder()
                .accommodationName(accommodation.getAccommodationName())
                .accommodationType(accommodation.getAccommodationType())
                .price(accommodation.getPrice())
                .address(accommodation.getAddress())
                .maximumNumberOfGuest(accommodation.getMaximumNumberOfGuest())
                .build();
    }
}
