package com.neutral.sync_youtube.youtube.domain;

import com.neutral.sync_youtube.common.domain.AbstractEntity;
import com.neutral.sync_youtube.common.domain.ICheckEditable;
import com.neutral.sync_youtube.common.exception.ForbiddenException;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.youtube.dto.YoutubeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Youtube")
@Getter
@NoArgsConstructor
public class Youtube extends AbstractEntity implements ICheckEditable {

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String thumbnail;

    private int seconds;

    @Builder
    public Youtube(Long id, User user, String title, String url, String thumbnail, int seconds) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
        this.seconds = seconds;
    }

    public Youtube(Long id, User user, String title, String url, String thumbnail) {
        this(id, user, title, url, thumbnail, 0);
    }

    public void modify(User user, YoutubeDTO dto) {
        if (checkEditable(user)) {
            throw new ForbiddenException();
        }

        this.title = !this.title.equals(dto.getTitle()) && StringUtils.hasText(dto.getTitle()) ? dto.getTitle() : this.title;
        this.url = !this.url.equals(dto.getUrl()) && StringUtils.hasText(dto.getUrl()) ? dto.getUrl() : this.url;
        this.thumbnail = !this.thumbnail.equals(dto.getThumbnail()) && StringUtils.hasText(dto.getThumbnail()) ? dto.getThumbnail() : this.thumbnail;
        this.seconds = dto.getSeconds();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Youtube youtube = (Youtube) o;
        return Objects.equals(this.id, youtube.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean checkEditable(User user) {
        return !user.equals(this.user);
    }
}
