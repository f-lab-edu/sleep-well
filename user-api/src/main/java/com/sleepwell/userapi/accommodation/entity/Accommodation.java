package com.sleepwell.userapi.accommodation.entity;

import com.sleepwell.userapi.accommodation.dto.AccommodationDetailInfoDto;
import com.sleepwell.userapi.accommodation.dto.AccommodationInfoDto;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Accommodation {

    private Long id;

    private String accommodationName;

    private int price;

    private String accommodationType;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private String checkInTime;

    private String checkOutTime;

    private int maximumNumberOfGuest;

    private String description;

    private Member host;

    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    private Accommodation(String accommodationName, int price, String accommodationType, String location, String checkInDate, String checkOutDate, String checkInTime, String checkOutTime, int maximumNumberOfGuest, String description) {
        this.accommodationName = accommodationName;
        this.price = price;
        this.accommodationType = accommodationType;
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.maximumNumberOfGuest = maximumNumberOfGuest;
        this.description = description;
    }

    public AccommodationInfoDto toAccommodationInfoDto() {
        return AccommodationInfoDto.builder()
                .accommodationName(this.accommodationName)
                .accommodationType(this.accommodationType)
                .price(this.price)
                .location(this.location)
                .maximumNumberOfGuest(this.maximumNumberOfGuest)
                .build();
    }

    public AccommodationDetailInfoDto toAccommodationDetailInfo() {
        return AccommodationDetailInfoDto.builder()
                .accommodationId(this.id)
                .accommodationName(this.accommodationName)
                .price(this.price)
                .accommodationType(this.accommodationType)
                .location(this.location)
                .checkInDate(this.checkInDate)
                .checkOutDate(this.checkOutDate)
                .checkInTime(this.checkInTime)
                .checkOutTime(checkOutTime)
                .maximumNumberOfGuest(this.maximumNumberOfGuest)
                .description(this.description)
                .build();
    }
}
