package com.accountpatrol.api.pentadata.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulerConfig {

    /**
     * Task scheduler definition for the pentadata transactions load.
     * @param corePoolSize
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler allocateTaskExecutor(final @Value("${pentadata.scheduler.corePoolSize:1}") int corePoolSize) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(corePoolSize);
        taskScheduler.setThreadNamePrefix("PT-TXN-Scheduler");
        taskScheduler.initialize();
        return taskScheduler;
    }

    @Bean(value = "pentadataThreadPoolExecutor")
    @Primary
    public ThreadPoolTaskExecutor allocateThreadPoolExecutor(final @Value("${pentadata.scheduler.corePoolSize:5}") int corePoolSize,
            final @Value("${pentadata.scheduler.maxPoolSize:10}") int maxPoolSize,
            final @Value("${pentadata.scheduler.queueCapacity:5000000}") int queueCapacity,
            final @Value("${pentadata.scheduler.threadPrefix:PT-TXN}") String threadPrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix(threadPrefix);
        return taskExecutor;
    }

}
