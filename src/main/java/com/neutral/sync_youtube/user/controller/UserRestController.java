package com.neutral.sync_youtube.user.controller;

import com.neutral.sync_youtube.common.support.RestResponse;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.user.service.UserService;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import com.neutral.sync_youtube.youtube.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserRestController {
    private final UserService userService;

    private final YoutubeService youtubeService;

    @Autowired
    public UserRestController(UserService userService, YoutubeService youtubeService) {
        this.userService = userService;
        this.youtubeService = youtubeService;
    }

    @GetMapping
    public RestResponse<List<User>> getAllUser() {
        return RestResponse.success(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public RestResponse<User> getUser(@PathVariable("id") Long id) {
        return RestResponse.success(userService.getUserById(id));
    }

    @PutMapping
    public RestResponse<User> modify(@RequestBody User user) {
        return RestResponse.success(userService.modify(user));
    }

    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return RestResponse.success();
    }

    @GetMapping("/{id}/youtube/")
    public RestResponse<List<Youtube>> getYoutubeListByUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return RestResponse.success(youtubeService.getYoutubeListByUser(user));
    }
}