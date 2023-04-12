package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.reservation.dto.ReservationInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    public Optional<ReservationInfoDto> getReservation(Long reservationId) {
        return Optional.ofNullable(ReservationInfoDto.builder()
                .reservationId(1L)
                .build());
    }

    public List<ReservationInfoDto> getReservationList(Long userId) {
        return List.of(ReservationInfoDto.builder()
                .reservationId(1L)
                .build());
    }

    public void deleteReservation(String reservationId) {

    }
}
