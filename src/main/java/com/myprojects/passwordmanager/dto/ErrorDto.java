package com.myprojects.passwordmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDto {

    private Integer statusCode;
    private String message;
    private String path;
    private Date timestamp;
}
