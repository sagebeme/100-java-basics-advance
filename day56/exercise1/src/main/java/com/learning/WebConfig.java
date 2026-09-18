package com.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Not under static/, so this path only works because of this handler - proves it's the
        // custom mapping doing the work, not Spring Boot's default static/ auto-serving.
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/custom-assets/");
    }
}
