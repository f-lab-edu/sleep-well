package com.sleepwell.userapi.accommodation.service;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public List<Accommodation> findAccommodation(AccommodationSearchDto accommodationSearchDto) {
        return accommodationRepository.findAllByAccommodationSearchDto(accommodationSearchDto);
    }

}
