package com.sleepwell.userapi.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepwell.userapi.accommodation.repository.AccommodationCustomRepository;
import com.sleepwell.userapi.accommodation.repository.AccommodationCustomRepositoryImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public AccommodationCustomRepository accommodationCustomRepository(JPAQueryFactory jpaQueryFactory) {
        return new AccommodationCustomRepositoryImpl(jpaQueryFactory);
    }
}
