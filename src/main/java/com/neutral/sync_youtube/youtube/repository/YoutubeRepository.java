package com.neutral.sync_youtube.youtube.repository;

import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
    List<Youtube> findAllByUser(User user);
}
