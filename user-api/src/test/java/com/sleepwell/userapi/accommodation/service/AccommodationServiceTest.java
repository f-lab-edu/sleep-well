package com.sleepwell.userapi.accommodation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    AccommodationService accommodationService;

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
