package com.sleepwell.userapi.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationSearchDto {

    private String type;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private int guests;
}
