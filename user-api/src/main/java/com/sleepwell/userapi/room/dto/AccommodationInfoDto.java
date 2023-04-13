package com.sleepwell.userapi.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationInfoDto {

    private String accommodationName;

    private String type;

    private String location;
}
