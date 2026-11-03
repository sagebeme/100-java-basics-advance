package com.learning;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // The menu board lives outside static/, so it's only reachable because of this handler.
    // Phones may keep a copy for a day: the menu changes at most once a day.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO: serve /menu/** from classpath:/menu-board/, with a one-day Cache-Control
    }
}
