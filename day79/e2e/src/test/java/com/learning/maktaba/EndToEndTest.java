package com.learning.maktaba;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

// The top of the pyramid. Spring starts the whole app on a real port; Chrome uses it like a reader would.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(EndToEndTest.FrozenTime.class)
abstract class EndToEndTest {

    // Every end-to-end test runs on Monday 5 October 2026, so due dates never depend on when you run them.
    @TestConfiguration
    static class FrozenTime {
        @Bean
        @Primary
        Clock frozenClock() {
            return Clock.fixed(Instant.parse("2026-10-05T06:00:00Z"), ZoneId.of("Africa/Nairobi"));
        }
    }

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private LibraryService library;

    protected WebDriver driver;

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    void freshShelvesAndBrowser() {
        library.reset(); // the server keeps running between tests, so put its data back first
        try {
            driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new", "--window-size=1280,900"));
        } catch (RuntimeException e) {
            Assumptions.abort("No Chrome available, so the end-to-end tests are skipped: "
                    + e.getMessage().lines().findFirst().orElse(""));
        }
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
