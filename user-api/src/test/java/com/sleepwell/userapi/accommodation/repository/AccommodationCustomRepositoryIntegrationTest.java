package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.config.TestConfig;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
@DataJpaTest
public class AccommodationCustomRepositoryIntegrationTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AccommodationCustomRepository accommodationCustomRepository;

    @BeforeEach
    void setup() {
        //given
        LocalDate reservedDate = LocalDate.of(2023, 6, 8);
        LocalDate checkInDate = LocalDate.of(2023, 6, 8);
        LocalDate checkOutDate = LocalDate.of(2023, 6, 10);

        for (int i = 5; i < 9; i++) {
            Reservation reservation = reservationRepository.save(new Reservation(checkInDate, checkOutDate, reservedDate, 1, 1000));
            Accommodation accommodation = accommodationRepository.save(new Accommodation("숙소 이름" + (i % 2), i * 1000, "숙소 타입" + (i % 2), "지역" + (i % 2), LocalTime.of(15, 0), LocalTime.of(11, 0), i, "세부 정보"));
            accommodation.getReservations().add(reservation);
            reservation.setAccommodation(accommodation);
        }
    }

    @DisplayName("저장된 숙소 중")
    @Nested
    class FindAllByAccommodationSearchDto {

        @DisplayName("숙소 이름이")
        @Nested
        class CheckAccommodationName {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto("숙소 이름3", null, null, null, null, null, null, null));

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto("숙소 이름1", null, null, null, null, null, null, null));

                //then
                assertEquals(2, result.size());
                assertEquals("숙소 이름1", result.get(0).getAccommodationName());
            }
        }

        @DisplayName("숙소 타입이")
        @Nested
        class CheckAccommodationType {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentAccommodationType() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, "숙소 타입3", null, null, null, null, null, null));

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualAccommodationType() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, "숙소 타입1", null, null, null, null, null, null));

                //then
                assertEquals(2, result.size());
                assertEquals("숙소 타입1", result.get(0).getAccommodationType());
            }
        }

        @DisplayName("지역이")
        @Nested
        class CheckLocation {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, "지역3", null, null, null, null, null));

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, "지역1", null, null, null, null, null));

                //then
                assertEquals(2, result.size());
                assertEquals("지역1", result.get(0).getLocation());
            }
        }

        @DisplayName("숙박 일자 사이에")
        @Nested
        class CheckCheckDate {

            @DisplayName("체크인 하는 예약 정보가 존재하면, 빈 리스트 반환")
            @Test
            void withInvalidCheckInDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, LocalDate.of(2023, 6, 8), null, null, null, null));

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("체크아웃 하는 예약 정보가 존재하면, 빈 리스트 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, LocalDate.of(2023, 6, 9), null, null, null));

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("예약이 가능한 모든 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, LocalDate.of(2022, 6, 8), LocalDate.of(2022, 6, 8), null, null, null));

                //then
                assertEquals(4, result.size());
            }
        }

        @DisplayName("금액이")
        @Nested
        class CheckPrice {

            @DisplayName("최소 금액만 존재하면, 해당 금액 이상의 숙소 반환")
            @Test
            void withMinPrice() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, null, 6000, null, null));

                //then
                assertEquals(3, result.size());
                assertTrue(result.get(0).getPrice() >= 6000);
            }

            @DisplayName("최대 금액만 존재하면, 해당 금액 이하의 숙소 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, null, null, 6000, null));

                //then
                assertEquals(2, result.size());
                assertTrue(result.get(0).getPrice() <= 6000);
            }

            @DisplayName("최소, 최대 금액이 모두 존재하면, 두 금액 사이의 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, null, 6000, 7000, null));

                //then
                assertEquals(2, result.size());
                assertTrue(result.get(0).getPrice() >= 6000);
                assertTrue(result.get(0).getPrice() <= 7000);
            }
        }

        @DisplayName("숙박 인원이")
        @Nested
        class CheckNumberOfGuest {

            @DisplayName("최대 숙박 인원보다 작은 숙소 정보 반환")
            @Test
            void withMinPrice() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, null, null, null, 3));

                //then
                assertEquals(4, result.size());
                assertTrue(result.get(0).getMaximumNumberOfGuest() >= 3);
            }

            @DisplayName("최대 숙박 인원보다 작은 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(new AccommodationSearchDto(null, null, null, null, null, null, null, 10));

                //then
                assertTrue(result.isEmpty());
            }
        }
    }
}
