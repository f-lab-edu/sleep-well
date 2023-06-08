package com.sleepwell.userapi.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@EntityScan("com.sleepwell.userapi.*.entity")
@EnableJpaRepositories("com.sleepwell.userapi.*.repository")
public class TestBatchConfig {
}

