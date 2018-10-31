package com.neutral.sync_youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SyncYoutubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncYoutubeApplication.class, args);
    }
}
