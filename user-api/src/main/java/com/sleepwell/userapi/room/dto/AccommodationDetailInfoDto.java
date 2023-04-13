package com.sleepwell.userapi.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationDetailInfoDto {

    private Long accommodationId;

    private String accommodationName;

    private String type;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private Integer guests;
}
