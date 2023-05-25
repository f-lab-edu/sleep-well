package com.sleepwell.userapi.accommodation.service;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationCustomRepository;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceTest {

    @Mock
    AccommodationRepository accommodationRepository;

    @Mock
    AccommodationCustomRepository accommodationCustomRepository;

    @InjectMocks
    AccommodationService accommodationService;

    @DisplayName("조건에 맞는 숙소가 존재하지 않을 시 예외 발생")
    @Test
    void findAccommodationWithInvalidSearchCondition() {
        //given
        AccommodationSearchDto accommodationSearchDto = mock(AccommodationSearchDto.class);
        when(accommodationCustomRepository.findAllByAccommodationSearchDto(any())).thenReturn(Collections.emptyList());

        //then
        assertThrows(RuntimeException.class, () -> accommodationService.findAccommodation(accommodationSearchDto));
    }

    @DisplayName("조건에 맞는 숙소가 존재할 시 숙소 리스트 반환")
    @Test
    void findAccommodationWithValidSearchCondition() {
        //given
        AccommodationSearchDto accommodationSearchDto = mock(AccommodationSearchDto.class);
        Accommodation accommodation = mock(Accommodation.class);
        when(accommodationCustomRepository.findAllByAccommodationSearchDto(any())).thenReturn(List.of(accommodation));

        //then
        List<Accommodation> accommodations = accommodationService.findAccommodation(accommodationSearchDto);
        assertEquals(List.of(accommodation), accommodations);
    }

    @DisplayName("존재하지 않는 숙소 조회 시 예외 발생")
    @Test
    void findAccommodationWithInvalidAccommodationId() {
        //given
        when(accommodationRepository.findById(any())).thenReturn(Optional.empty());

        //then
        assertThrows(RuntimeException.class, () -> accommodationService.getAccommodation(1L));
    }

    @DisplayName("유효한 숙소 조회 시 숙소 반환")
    @Test
    void findAccommodationWithValidAccommodationId() {
        //given
        Accommodation mockAccommodation = mock(Accommodation.class);
        when(accommodationRepository.findById(any())).thenReturn(Optional.of(mockAccommodation));

        //then
        Accommodation findAccommodation = accommodationService.getAccommodation(1L);
        assertEquals(mockAccommodation, findAccommodation);
    }
}
