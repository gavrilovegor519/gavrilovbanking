package com.egor.gavrilovbanking.repo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import com.egor.gavrilovbanking.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    @NotNull
    User findUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
