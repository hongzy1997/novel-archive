package com.hongzy.novelarchive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web共通設定
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** CORS許可オリジン */
    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;

    /**
     * CORS設定
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigin)
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE");
    }
}