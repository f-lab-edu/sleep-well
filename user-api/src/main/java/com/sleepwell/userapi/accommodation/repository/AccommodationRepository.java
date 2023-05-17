package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    public Optional<Accommodation> findById(@NotNull Long accommodationId);

    //동적쿼리 사용 예정
    public List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto);

}
