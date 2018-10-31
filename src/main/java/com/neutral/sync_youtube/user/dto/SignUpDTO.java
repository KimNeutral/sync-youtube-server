package com.neutral.sync_youtube.user.dto;

import com.neutral.sync_youtube.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class SignUpDTO {
    @NotNull
    @Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    private String password;

    @Builder
    public SignUpDTO(@NotNull @Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            flags = Pattern.Flag.CASE_INSENSITIVE) String email, @NotNull String name, @NotNull String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .name(this.name)
                .build();
    }
}
