package com.example.handoveriq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Poore project ke sabhi URLs par लागू hoga
                .allowedOrigins( "https://handoveriq-frontend.vercel.app",
                        "http://localhost:5500",
                        "http://127.0.0.1:5500") // Tumhare frontend ka URL (ya "*" bhi laga sakte ho sabke liye)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
