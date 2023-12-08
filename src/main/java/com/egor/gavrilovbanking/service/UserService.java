package com.egor.gavrilovbanking.service;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.exceptions.DuplicateUser;
import com.egor.gavrilovbanking.exceptions.IncorrectPassword;
import com.egor.gavrilovbanking.exceptions.UserNotFound;

public interface UserService {
    String login(LoginDTO login) throws UserNotFound, IncorrectPassword;
    void reg(UserDTO userData) throws DuplicateUser;
}
