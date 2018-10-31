package com.neutral.sync_youtube.youtube.dto;

import com.neutral.sync_youtube.common.exception.BadRequestException;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class YoutubeDTO {
    private String title;

    @NotNull
    @NotBlank
    private String url;

    private String thumbnail;

    private int seconds;

    @Builder
    public YoutubeDTO(String title, @NotNull @NotBlank String url, String thumbnail, int seconds) {
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
        this.seconds = seconds;
    }

    public Youtube toEntity(User owner) {
        if (owner == null) throw new BadRequestException();

        return Youtube.builder()
                .user(owner)
                .title(this.title)
                .url(this.url)
                .thumbnail(this.thumbnail)
                .seconds(this.seconds)
                .build();
    }
}
