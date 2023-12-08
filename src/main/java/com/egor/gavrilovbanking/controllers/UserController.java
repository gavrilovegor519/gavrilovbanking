package com.egor.gavrilovbanking.controllers;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.exceptions.DuplicateUser;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public String getToken(@RequestBody LoginDTO loginDTO) throws UserNotFound {
        return userService.login(loginDTO);
    }

    @PostMapping("/user/reg")
    public void postMethodName(@RequestBody UserDTO userDTO) throws DuplicateUser {
        userService.reg(userDTO);
    }
    
}
