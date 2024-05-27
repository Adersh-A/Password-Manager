package com.myprojects.passwordmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
