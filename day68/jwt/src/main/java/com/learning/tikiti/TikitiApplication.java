package com.learning.tikiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class TikitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikitiApplication.class, args);
    }

    // Everything that checks the time asks this clock, so the tests can move time forward instead of waiting 15 minutes.
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
