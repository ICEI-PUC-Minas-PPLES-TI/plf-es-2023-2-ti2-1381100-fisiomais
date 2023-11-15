package com.fisiomais.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173") // TODO - Change this to the frontend URL
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
        // Add more configurations as needed
    }
}
