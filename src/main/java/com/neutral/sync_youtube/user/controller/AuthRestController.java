package com.neutral.sync_youtube.user.controller;

import com.neutral.sync_youtube.common.security.JwtTokenProvider;
import com.neutral.sync_youtube.common.security.UserPrincipal;
import com.neutral.sync_youtube.common.support.RestResponse;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.user.dto.LoginDTO;
import com.neutral.sync_youtube.user.dto.SignUpDTO;
import com.neutral.sync_youtube.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthRestController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public RestResponse<String> login(@RequestBody LoginDTO dto) {
        User user = userService.login(dto);
        UserDetails userDetails = UserPrincipal.create(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        String token = jwtTokenProvider.generateToken(authentication);

        return RestResponse.success(token);
    }

    @PostMapping("/signup")
    public RestResponse<User> signup(@RequestBody SignUpDTO dto) {
        User user = userService.create(dto);
        return RestResponse.success(user);
    }
}
