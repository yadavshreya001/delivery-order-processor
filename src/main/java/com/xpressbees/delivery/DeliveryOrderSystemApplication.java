package com.xpressbees.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DeliveryOrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryOrderSystemApplication.class, args);
    }
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5); // 5 delivery agents
    }

}
