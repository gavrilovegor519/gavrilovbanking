package com.egor.gavrilovbanking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egor.gavrilovbanking.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
