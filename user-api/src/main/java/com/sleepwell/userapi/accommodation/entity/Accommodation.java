package com.sleepwell.userapi.accommodation.entity;

import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    private int maximumNumberOfGuest;

    private String description;

    private Member host;

    private List<Reservation> reservations = new ArrayList<>();

    public Accommodation(String accommodationName, int price, String accommodationType, String location, LocalDate checkInDate, LocalDate checkOutDate, LocalTime checkInTime, LocalTime checkOutTime, int maximumNumberOfGuest, String description) {
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
}
