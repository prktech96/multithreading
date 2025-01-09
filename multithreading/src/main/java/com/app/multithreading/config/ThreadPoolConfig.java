package com.app.multithreading.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService executorService() {
        int threadPoolSize = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(threadPoolSize);
    }
    
    @PreDestroy
    public void shutDownExecutorService(@Autowired ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}


