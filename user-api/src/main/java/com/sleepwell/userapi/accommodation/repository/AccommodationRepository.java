package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

}
