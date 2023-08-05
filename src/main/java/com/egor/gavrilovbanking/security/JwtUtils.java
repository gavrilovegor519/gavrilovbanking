package com.egor.gavrilovbanking.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${com.egor.jwtSecret}")
    private String jwtSecret;

    @Value("${com.egor.jwtExpirationMs}")
    private int jwtExpirationMs;
}
