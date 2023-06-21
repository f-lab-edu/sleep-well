package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.entity.Address;
import com.sleepwell.userapi.config.TestConfig;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(TestConfig.class)
@Transactional(value = "jpaTransactionManager")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
            Reservation reservation = reservationRepository.save(new Reservation(checkInDate, checkOutDate, reservedDate, ReservationStatus.RESERVED, 1, 1000));
            Accommodation accommodation = accommodationRepository.save(new Accommodation("숙소 이름" + (i % 2), i * 1000, "숙소 타입" + (i % 2), new Address("도로명" + (i % 2), "상세 주소" + (i % 2), "우편번호" + (i % 2)), LocalTime.of(15, 0), LocalTime.of(11, 0), i, "세부 정보"));
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
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().accommodationName("숙소 이름3").build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().accommodationName("숙소 이름1").build());

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
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().accommodationType("숙소 타입3").build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualAccommodationType() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().accommodationType("숙소 타입1").build());

                //then
                assertEquals(2, result.size());
                assertEquals("숙소 타입1", result.get(0).getAccommodationType());
            }
        }

        @DisplayName("도로명이")
        @Nested
        class CheckStreetAddress {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().streetAddress("도로명3").build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().streetAddress("도로명1").build());

                //then
                assertEquals(2, result.size());
                assertEquals("도로명1", result.get(0).getAddress().getStreetAddress());
            }
        }

        @DisplayName("상세 주소가")
        @Nested
        class CheckDetailAddress {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().detailAddress("상세 주소3").build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().detailAddress("상세 주소1").build());

                //then
                assertEquals(2, result.size());
                assertEquals("상세 주소1", result.get(0).getAddress().getDetailAddress());
            }
        }

        @DisplayName("우편번호가")
        @Nested
        class CheckPostcode {

            @DisplayName("일치하는 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withDifferentLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().postcode("우편번호3").build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("일치하는 모든 숙소 반환")
            @Test
            void withEqualLocation() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().postcode("우편번호1").build());

                //then
                assertEquals(2, result.size());
                assertEquals("우편번호1", result.get(0).getAddress().getPostcode());
            }
        }

        @DisplayName("숙박 일자 사이에")
        @Nested
        class CheckCheckDate {

            @DisplayName("체크인 하는 예약 정보가 존재하면, 빈 리스트 반환")
            @Test
            void withInvalidCheckInDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().checkInDate(LocalDate.of(2023, 6, 8)).build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("체크아웃 하는 예약 정보가 존재하면, 빈 리스트 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().checkOutDate(LocalDate.of(2023, 6, 9)).build());

                //then
                assertTrue(result.isEmpty());
            }

            @DisplayName("예약이 가능한 모든 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().checkInDate(LocalDate.of(2023, 6, 8)).checkOutDate(LocalDate.of(2023, 6, 8)).build());

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
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().minPrice(6000).build());

                //then
                assertEquals(3, result.size());
                assertTrue(result.get(0).getPrice() >= 6000);
            }

            @DisplayName("최대 금액만 존재하면, 해당 금액 이하의 숙소 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().maxPrice(6000).build());

                //then
                assertEquals(2, result.size());
                assertTrue(result.get(0).getPrice() <= 6000);
            }

            @DisplayName("최소, 최대 금액이 모두 존재하면, 두 금액 사이의 숙소 반환")
            @Test
            void withEqualAccommodationName() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().minPrice(6000).maxPrice(7000).build());

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
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().numberOfGuest(3).build());

                //then
                assertEquals(4, result.size());
                assertTrue(result.get(0).getMaximumNumberOfGuest() >= 3);
            }

            @DisplayName("최대 숙박 인원보다 작은 숙소가 없으면, 빈 리스트 반환")
            @Test
            void withInvalidCheckOutDate() {
                //when
                List<Accommodation> result = accommodationCustomRepository.findAllByAccommodationSearchDto(AccommodationSearchDto.builder().numberOfGuest(10).build());

                //then
                assertTrue(result.isEmpty());
            }
        }
    }
}
