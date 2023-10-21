package com.egor.gavrilovbanking.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public void resolveAndWriteException(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(500);
    }
}
