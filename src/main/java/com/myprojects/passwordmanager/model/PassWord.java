package com.myprojects.passwordmanager.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class PassWord {

    private String appName;
    private String passWord;
    private Date createdTimestamp ;
    private Date updatedTimestamp;


}
