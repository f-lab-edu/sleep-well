package com.sleepwell.userapi.accommodation.controller;

import com.sleepwell.userapi.accommodation.dto.AccommodationDetailInfoDto;
import com.sleepwell.userapi.accommodation.dto.AccommodationInfoDto;
import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    /**
     * 원하는 방 목록 조회 기능
     */
    @GetMapping
    public List<AccommodationInfoDto> findAccommodation(@RequestBody AccommodationSearchDto accommodationSearchDto) {
        return accommodationService.findAccommodation(accommodationSearchDto).stream()
                .map(AccommodationInfoDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 방 상세 정보 조회 기능
     */
    @GetMapping("/{accommodationId}")
    public AccommodationDetailInfoDto getAccommodation(@PathVariable(name = "accommodationId") Long accommodationId) {
         Accommodation accommodation = accommodationService.getAccommodation(accommodationId);
        return AccommodationDetailInfoDto.fromEntity(accommodation);
    }
}
