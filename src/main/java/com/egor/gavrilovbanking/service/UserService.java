package com.egor.gavrilovbanking.service;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;

public interface UserService {
    String login(LoginDTO login);
    void reg(UserDTO userData);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
