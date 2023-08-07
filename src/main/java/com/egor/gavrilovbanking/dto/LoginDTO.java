package com.egor.gavrilovbanking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;
}
