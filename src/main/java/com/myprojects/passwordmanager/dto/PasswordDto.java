package com.myprojects.passwordmanager.dto;

import com.myprojects.passwordmanager.model.PassWord;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PasswordDto {
    private String appName;
    private String passWord;


}
