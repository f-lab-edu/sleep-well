package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationInfoDto {

    private String accommodationName;

    private String accommodationType;

    private int price;

    private String location;

    private int maximumNumberOfGuest;

}
