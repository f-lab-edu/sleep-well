package com.sleepwell.userapi.accommodation.service;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationCustomRepository;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
import com.sleepwell.userapi.error.ErrorStatus;
import com.sleepwell.userapi.error.exception.BaseException;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationCustomRepository accommodationCustomRepository;

    public List<Accommodation> findAccommodation(AccommodationSearchDto accommodationSearchDto) {
        List<Accommodation> accommodations = accommodationCustomRepository.findAllByAccommodationSearchDto(accommodationSearchDto);

        if (accommodations.isEmpty()) {
            throw new BaseException(ErrorStatus.ROOM_NOT_FOUND);
        }
        return accommodations;
    }

    public Accommodation createAccommodation(Long memberId, Accommodation accommodation) {
        Member member = memberRepository.getReferenceById(memberId);
        accommodation.setHost(member);
        return accommodationRepository.save(accommodation);
    }

    public Accommodation getAccommodation(Long accommodationId) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);

        return accommodation.orElseThrow(() -> new BaseException(ErrorStatus.ROOM_NOT_FOUND));
    }
}
