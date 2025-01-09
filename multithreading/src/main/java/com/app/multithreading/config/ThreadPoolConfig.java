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
}


