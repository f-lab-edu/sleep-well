package com.sleepwell.userapi.member.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Member {

    private Long id;

    private String name;

    private String email;

    private String password;

    private List<Reservation> reservations = new ArrayList<>();

    private List<Accommodation> accommodations = new ArrayList<>();

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
