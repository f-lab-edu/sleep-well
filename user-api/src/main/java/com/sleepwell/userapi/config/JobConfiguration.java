package com.sleepwell.userapi.config;

import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 100;
    private static final int PAYMENT_GRACE_PERIOD = 7;

    @Bean
    public Job cancelNotPayedReservationJob() {
        return jobBuilderFactory.get("cancelNotPayedReservationJob")
                .start(cancelNotPayedReservationStep())
                .build();
    }

    @Bean
    @StepScope
    public Step cancelNotPayedReservationStep() {
        return stepBuilderFactory.get("cancelNotPayedReservationStep")
                .<Reservation, Reservation>chunk(CHUNK_SIZE)
                .reader(notPayPayedReservationReader())
                .processor(cancelNotPayedReservationProcessor())
                .writer(cancelNotPayedReservationWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Reservation> notPayPayedReservationReader() {
        HashMap<String, Object> paramValues = new HashMap<>();
        paramValues.put("deadline", LocalDate.now().minusDays(PAYMENT_GRACE_PERIOD));

        return new JpaPagingItemReaderBuilder<Reservation>()
                .name("notPayPayedReservationReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select r from Reservation r where r.reservedDate <= : and r.reservationStatus = BEFORE_PAYED")
                .parameterValues(paramValues)
                .pageSize(CHUNK_SIZE)
                .build();
    }

    private ItemProcessor<Reservation, Reservation> cancelNotPayedReservationProcessor() {
        return reservation -> {
            reservation.setReservationStatus(ReservationStatus.CANCELED);
            return reservation;
        };
    }

    @Bean
    @StepScope
    public ItemWriter<? super Reservation> cancelNotPayedReservationWriter() {
        return new JpaItemWriterBuilder<Reservation>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
