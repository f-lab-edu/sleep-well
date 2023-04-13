package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.reservation.dto.ReservationInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    public ReservationInfoDto getReservation(Long reservationId) {
        //예약 정보 조회
        Optional<ReservationInfoDto> reservationInfo = Optional.ofNullable(ReservationInfoDto.builder()
                .reservationId(reservationId)
                .build());

        //존재하지 않는 예약정보 조회 불가
        if (reservationInfo.isEmpty()) {
            throw new RuntimeException("존재하지 않는 예약 정보입니다.");
        }
        return reservationInfo.get();
    }

    public List<ReservationInfoDto> getReservationList(Long userId) {
        return List.of(ReservationInfoDto.builder()
                .reservationId(1L)
                .build());
    }

    public ReservationInfoDto deleteReservation(Long reservationId) {
        //실제 예약정보가 실존하는지 확인
        //연관된 숙소 정보 업데이트
        //실제 객체 리턴
        return ReservationInfoDto.builder()
                .reservationId(reservationId)
                .build();
    }
}
