package com.neutral.sync_youtube.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neutral.sync_youtube.common.domain.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor
public class User extends AbstractEntity {
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Builder
    public User(Long id, String email, String name, String password) {
        this(email, name, password);
        this.id = id;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
