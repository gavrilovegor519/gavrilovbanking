package com.egor.gavrilovbanking.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(IllegalArgumentException.class)
    public void resolveAndWriteException(HttpServletResponse response) {
        response.setStatus(400);
    }
}
