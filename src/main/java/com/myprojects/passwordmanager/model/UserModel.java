package com.myprojects.passwordmanager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "passwords")
public class UserModel {

    @Id
    private String id;
    private String userName;
    private List<PassWord> passWordList;

}
