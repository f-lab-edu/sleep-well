package com.sleepwell.userapi.member.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany
    private List<Accommodation> accommodations = new ArrayList<>();

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
