package com.sleepwell.userapi.util;

import com.sleepwell.userapi.config.JobConfiguration;
import com.sleepwell.userapi.log.LogMessage;
import com.sleepwell.userapi.log.kafka.LogProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.logging.LogLevel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final LogProducer logProducer;
    private final JobLauncher jobLauncher;
    private final JobConfiguration jobConfiguration;

    @Scheduled(cron = "* * 0 * * *", zone = "Asia/Seoul")
    public void cancelNotPayedReservations() {
        try {
            logProducer.send(new LogMessage("미결제 예약에 대한 취소 배치 작업 시작", LogLevel.INFO));
            jobLauncher.run(jobConfiguration.cancelNotPayedReservationJob(), new JobParameters());
            logProducer.send(new LogMessage("미결제 예약에 대한 취소 배치 작업 정상 종료", LogLevel.INFO));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            logProducer.send(new LogMessage("미결제 예약에 대한 취소 배치 작업 비정상 종료 : " + e.getMessage(), LogLevel.INFO));
        }
    }
}
