package com.myprojects.passwordmanager.repository;

import com.myprojects.passwordmanager.dto.PasswordDto;
import com.myprojects.passwordmanager.model.PassWord;
import com.myprojects.passwordmanager.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PassWordRepository extends MongoRepository<UserModel,String> {

    public UserModel findUserByUserName(String userName);

}
