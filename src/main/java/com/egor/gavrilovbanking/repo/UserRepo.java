package com.egor.gavrilovbanking.repo;

import com.egor.gavrilovbanking.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    @NotNull
    User findUserByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByTel(long tel);

    boolean existsUserByEmail(String email);
}
