package com.sleepwell.userapi.reservation.controller;

import com.sleepwell.userapi.reservation.dto.ReservationInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import com.sleepwell.userapi.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 사용자의 전체 예약 정보 조회
     */
    @GetMapping
    public List<ReservationInfoDto> getReservationList() {
        Long userId = 1L;
        return reservationService.getReservationList(userId);
    }

    /**
     * 숙소 예약
     */
    @PostMapping
    public ReservationInfoDto makeReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        return ReservationInfoDto.builder()
                .reservationId(reservationRequestDto.getReservationId())
                .build();
    }

    /**
     * 단일 예약 정보 조회
     */
    @GetMapping("/{reservationId}")
    public ReservationInfoDto getReservation(@PathVariable(name = "reservationId") Long reservationId) {
        return reservationService.getReservation(reservationId);
    }

    /**
     * 예약 취소
     */
    @DeleteMapping("{reservationId}")
    public ReservationInfoDto deleteReservation(@PathVariable(name = "reservationId") Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
