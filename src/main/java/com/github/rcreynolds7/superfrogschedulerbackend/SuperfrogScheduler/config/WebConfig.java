package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow this origin to make requests
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the allowed methods
                .allowedHeaders("*"); // Allow all headers

    }
}
