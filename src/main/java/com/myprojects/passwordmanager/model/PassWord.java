package com.myprojects.passwordmanager.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PassWord {

    private String appName;
    private String passWord;
    private Date createdTimestamp;
    private Date updatedTimestamp;


}
