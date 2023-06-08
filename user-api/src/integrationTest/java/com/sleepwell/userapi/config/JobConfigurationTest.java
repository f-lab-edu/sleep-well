package com.sleepwell.userapi.config;

import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBatchTest
@SpringBootTest(classes = {TestBatchConfig.class, JobConfiguration.class})
class JobConfigurationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("기간 내 예약이 완료되지 않은 예약의 상태를 CANCEL 시킨다.")
    @Test
    void cancelReservationTest() throws Exception {
        //given
        for (int i = 0; i < 10; i++) {
            reservationRepository.save(new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().minusDays(7), ReservationStatus.BEFORE_PAYED, 5, 1000));
        }

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //then
        List<Reservation> result = reservationRepository.findAll();
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(10, result.size());
        assertEquals(ReservationStatus.CANCELED, result.get(0).getReservationStatus());
    }
}

