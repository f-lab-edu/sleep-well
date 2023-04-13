package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.reservation.dto.ReservationDetailInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    public ReservationDetailInfoDto createReservation(ReservationRequestDto reservationRequestDto) {
        Long reservationId = 1L; //repository.save();

        return ReservationDetailInfoDto.builder()
                .reservationId(reservationId)
                .accommodationId(1L)
                .accommodationName("숙소 이름")
                .type("아파트")
                .checkInDate("2023-08-01")
                .checkOutDate("2023-08-04")
                .guests(4)
                .build();
    }

    public ReservationDetailInfoDto getReservation(Long reservationId) {
        //repository.findById();
        Optional<ReservationDetailInfoDto> reservationInfo = Optional.ofNullable(ReservationDetailInfoDto.builder()
                .reservationId(reservationId)
                .build());

        //존재하지 않는 예약정보 조회 불가
        if (reservationInfo.isEmpty()) {
            throw new RuntimeException("존재하지 않는 예약 정보입니다.");
        }
        return reservationInfo.get();
    }
}
