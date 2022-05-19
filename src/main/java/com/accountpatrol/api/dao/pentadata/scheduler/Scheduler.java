package com.accountpatrol.api.dao.pentadata.scheduler;

import com.accountpatrol.api.service.pentadata.impl.ScheduledService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Scheduler which fetches the transactions for the user based on the from and to Date defined in the properties.
 */
@Component
@EnableScheduling
@EnableAsync
public class Scheduler {

    private Logger logger = Logger.getLogger(Scheduler.class);
    private final ScheduledService service;

    public Scheduler(ScheduledService pService) {
        service = pService;
    }


    @Scheduled(cron = "${pentadata.job.schedule:0 */4 * * * ?}")
    @Async
    public void fetchTransactions(){
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        logger.debug("Scheduler kicked off at  [date=" + date + "]");
        service.loadTransactions();
    }
}
