package com.sleepwell.userapi.accommodation.entity;

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

    private String name;

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
    public Accommodation(String name, int price, String accommodationType, String location, String checkInDate, String checkOutDate, String checkInTime, String checkOutTime, int maximumNumberOfGuest, String description) {
        this.name = name;
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
}
