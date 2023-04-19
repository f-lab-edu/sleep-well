package com.sleepwell.userapi.accommodation.controller;

import com.sleepwell.userapi.accommodation.dto.AccommodationDetailInfoDto;
import com.sleepwell.userapi.accommodation.dto.AccommodationInfoDto;
import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class AccommodationController {

    /**
     * 원하는 방 목록 조회 기능
     */
    @GetMapping
    public List<AccommodationInfoDto> findAccommodation(@RequestBody AccommodationSearchDto accommodationSearchDto) {
        // entity = accommodationSearchDto.toEntity();
        // accommodationService.findAccommodation(entity);
        return List.of(AccommodationInfoDto.builder()
                .accommodationName("숙소 이름")
                .location("지역")
                .accommodationType("숙소 타입")
                .price(1)
                .build());
    }

    /**
     * 방 상세 정보 조회 기능
     */
    @GetMapping("/{accommodationId}")
    public AccommodationDetailInfoDto getAccommodation(@PathVariable(name = "accommodationId") Long accommodationId) {
        // entity = accommodationService.getAccommodation(accommodationId);
        return AccommodationDetailInfoDto.builder()
                .accommodationId(accommodationId)
                .accommodationName("숙소 이름")
                .price(1_000_000)
                .accommodationType("숙소 타입")
                .location("지역")
                .checkInDate("2023-08-01")
                .checkOutDate("2023-08-01")
                .checkInTime("15:00")
                .checkOutTime("13:00")
                .maximumNumberOfGuest(5)
                .description("세부 설명")
                .build();
    }
}
