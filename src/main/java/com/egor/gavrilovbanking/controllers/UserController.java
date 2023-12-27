package com.egor.gavrilovbanking.controllers;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.exceptions.DuplicateUser;
import com.egor.gavrilovbanking.exceptions.IncorrectPassword;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody LoginDTO loginDTO) throws UserNotFound, IncorrectPassword {
        return userService.login(loginDTO);
    }

    @PostMapping("/user/reg")
    public void reg(@RequestBody UserDTO userDTO) throws DuplicateUser {
        userService.reg(userDTO);
    }
    
}
