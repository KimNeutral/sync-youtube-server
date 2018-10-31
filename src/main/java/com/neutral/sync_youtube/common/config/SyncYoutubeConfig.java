package com.neutral.sync_youtube.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public abstract class SyncYoutubeConfig implements WebMvcConfigurer {
    @Configuration
    @Profile("development")
    static class SyncYoutubeDevConfig extends SyncYoutubeConfig {


    }

    @Configuration
    @Profile("local")
    static class SyncYoutubeLocalConfig extends SyncYoutubeConfig {

    }

    @Configuration
    @Profile("production")
    static class SyncYoutubeProdConfig extends SyncYoutubeConfig {


    }
}
