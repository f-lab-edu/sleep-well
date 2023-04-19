package com.sleepwell.userapi.accommodation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationDetailInfoDto {

    private Long accommodationId;

    private String accommodationName;

    private int price;

    private String accommodationType;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private String checkInTime;

    private String checkOutTime;

    private Integer maximumNumberOfGuest;

    private String description;

}
