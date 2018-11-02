package com.neutral.sync_youtube.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public abstract class SyncYoutubeConfig implements WebMvcConfigurer {
    @Configuration
    @Profile("development")
    static class SyncYoutubeDevConfig extends SyncYoutubeConfig {


    }

    @Configuration
    @Profile("local")
    static class SyncYoutubeLocalConfig extends SyncYoutubeConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**").allowedOrigins("chrome-extension://ekbnmoongdoofmppgageceppphpifllf");
                }
            };
        }
    }

    @Configuration
    @Profile("production")
    static class SyncYoutubeProdConfig extends SyncYoutubeConfig {


    }
}
