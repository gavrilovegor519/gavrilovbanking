package com.egor.gavrilovbanking.repo;

import com.egor.gavrilovbanking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByTel(long tel);

    boolean existsUserByEmail(String email);
}
