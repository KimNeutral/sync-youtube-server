package com.neutral.sync_youtube.user.service;

import com.neutral.sync_youtube.common.exception.ResourceExistsException;
import com.neutral.sync_youtube.common.exception.UnauthorizedException;
import com.neutral.sync_youtube.user.domain.User;
import com.neutral.sync_youtube.user.dto.LoginDTO;
import com.neutral.sync_youtube.user.dto.SignUpDTO;
import com.neutral.sync_youtube.user.repository.UserRepository;
import com.neutral.sync_youtube.youtube.domain.Youtube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(SignUpDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceExistsException();
        }
        User entity = dto.toEntity(passwordEncoder);

        return userRepository.save(entity);
    }

    public User login(LoginDTO dto) {
        return userRepository.findByEmail(dto.getEmail())
                .filter(user -> user.matchPassword(dto.getPassword(), passwordEncoder))
                .orElseThrow(UnauthorizedException::new);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User modify(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
