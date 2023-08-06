package com.egor.gavrilovbanking.service;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;

public interface UserService {
    String login(LoginDTO login);
    void reg(UserDTO userData);
}
