package com.neutral.sync_youtube.youtube.service;

import com.neutral.sync_youtube.common.exception.ForbiddenException;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import com.neutral.sync_youtube.youtube.dto.YoutubeDTO;
import com.neutral.sync_youtube.youtube.repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class YoutubeService {
    private final YoutubeRepository youtubeRepository;

    @Autowired
    public YoutubeService(YoutubeRepository youtubeRepository) {
        this.youtubeRepository = youtubeRepository;
    }

    public List<Youtube> getAllYoutube() {
        return youtubeRepository.findAll();
    }

    public Youtube getYoutubeById(Long id) {
        return youtubeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Youtube> getYoutubeListByUser(User user) {
        return youtubeRepository.findAllByUser(user);
    }

    public Youtube create(User user, YoutubeDTO youtubeDTO) {
        Youtube youtube = youtubeDTO.toEntity(user);

        return youtubeRepository.save(youtube);
    }

    public Youtube modify(User user, Youtube youtube, YoutubeDTO youtubeDTO) {
        if (youtube.checkEditable(user)) {
            throw new ForbiddenException();
        }
        youtube.modify(user, youtubeDTO);
        return youtubeRepository.save(youtube);
    }

    public void delete(User user, Youtube youtube) {
        if (youtube.checkEditable(user)) {
            throw new ForbiddenException();
        }
        youtubeRepository.delete(youtube);
    }
}
