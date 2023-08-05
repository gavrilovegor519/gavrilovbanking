package com.egor.gavrilovbanking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egor.gavrilovbanking.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}