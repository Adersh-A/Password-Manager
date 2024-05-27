package com.myprojects.passwordmanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordDto {
    private String appName;
    private String passWord;


}
