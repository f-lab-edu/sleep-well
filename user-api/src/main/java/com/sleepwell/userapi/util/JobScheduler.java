package com.sleepwell.userapi.util;

import com.sleepwell.userapi.config.JobConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final JobConfiguration jobConfiguration;

    @Scheduled(cron = "* * 0 * * *", zone = "Asia/Seoul")
    public void cancelNotPayedReservations() {
        try {
            log.info("미결제 예약에 대한 취소 배치 작업 시작");
            jobLauncher.run(jobConfiguration.cancelNotPayedReservationJob(), new JobParameters());
            log.info("미결제 예약에 대한 취소 배치 작업 정상 종료");
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            log.info("미결제 예약에 대한 취소 배치 작업 비정상 종료 : {}", e.getMessage());
        }
    }
}
