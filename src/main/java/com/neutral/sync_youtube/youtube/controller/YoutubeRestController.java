package com.neutral.sync_youtube.youtube.controller;

import com.neutral.sync_youtube.common.security.UserPrincipal;
import com.neutral.sync_youtube.common.support.RestResponse;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import com.neutral.sync_youtube.youtube.dto.YoutubeDTO;
import com.neutral.sync_youtube.youtube.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/youtube")
@CrossOrigin
public class YoutubeRestController {
    private final YoutubeService youtubeService;

    @Autowired
    public YoutubeRestController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping
    public RestResponse<List<Youtube>> getAllYoutube(@AuthenticationPrincipal UserPrincipal principal) {
        return RestResponse.success(youtubeService.getAllYoutube());
    }

    @GetMapping("/{id}")
    public RestResponse<Youtube> getYoutubeById(@PathVariable("id") Long id) {
        return RestResponse.success(youtubeService.getYoutubeById(id));
    }

    @PostMapping
    public RestResponse<Youtube> create(@AuthenticationPrincipal UserPrincipal principal,
                                        @RequestBody YoutubeDTO youtubeDTO) {
        return RestResponse.success(youtubeService.create(principal.toEntity(), youtubeDTO));
    }

    @PutMapping("/{id}")
    public RestResponse<Youtube> modify(@AuthenticationPrincipal UserPrincipal principal,
                                        @PathVariable("id") Long id,
                                        @RequestBody YoutubeDTO youtubeDTO) {
        Youtube youtube = youtubeService.getYoutubeById(id);
        return RestResponse.success(youtubeService.modify(principal.toEntity(), youtube, youtubeDTO));
    }

    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@AuthenticationPrincipal UserPrincipal principal,
                                     @PathVariable("id") Long id) {
        Youtube youtube = youtubeService.getYoutubeById(id);
        youtubeService.delete(principal.toEntity(), youtube);
        return RestResponse.success();
    }
}
