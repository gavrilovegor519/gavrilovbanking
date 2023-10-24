package com.egor.gavrilovbanking.controllers;

import com.egor.gavrilovbanking.exceptions.UserNotFound;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException(HttpServletResponse response) {
        response.setStatus(400);
    }

    @ExceptionHandler(UserNotFound.class)
    public void userNotFoundException(HttpServletResponse response) {
        response.setStatus(403);
    }
}
