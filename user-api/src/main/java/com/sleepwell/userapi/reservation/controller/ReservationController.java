package com.sleepwell.userapi.reservation.controller;

import com.sleepwell.userapi.reservation.dto.ReservationInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import com.sleepwell.userapi.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<ReservationInfoDto> getReservation(@PathVariable(name = "reservationId") Long reservationId) {
        return reservationService.getReservation(reservationId);
    }

    /**
     * 예약 취소
     */
    @DeleteMapping("{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable(name = "reservationId") String reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Ok");
    }
}
