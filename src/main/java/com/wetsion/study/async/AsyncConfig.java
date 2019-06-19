package com.wetsion.study.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncConfig
 * @date 2019/6/12 10:52 AM
 */
@Configuration
public class AsyncConfig {

    /** 初始线程数. */
    private int corePoolSize = 10;
    /** 最大线程数. */
    private int maxPoolSize = 200;
    /** 阻塞队列大小. */
    private int queueCapacity = 10;

    @Bean
    public Executor mySimpleAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MySimpleExecutor-");
        executor.initialize();
        return executor;
    }
}
