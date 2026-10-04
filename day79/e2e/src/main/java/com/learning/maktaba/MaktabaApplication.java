package com.learning.maktaba;

import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MaktabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaktabaApplication.class, args);
    }

    // Due dates come from this clock, so a test can freeze time instead of depending on today's date.
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
