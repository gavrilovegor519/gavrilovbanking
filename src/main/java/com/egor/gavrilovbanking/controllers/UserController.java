package com.egor.gavrilovbanking.controllers;

import org.springframework.web.bind.annotation.*;

import com.egor.gavrilovbanking.dto.*;
import com.egor.gavrilovbanking.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public String getToken(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/user/reg")
    public void postMethodName(@RequestBody UserDTO userDTO) {
        userService.reg(userDTO);
    }
    
}