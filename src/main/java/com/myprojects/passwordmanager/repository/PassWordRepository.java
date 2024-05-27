package com.myprojects.passwordmanager.repository;

import com.myprojects.passwordmanager.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassWordRepository extends MongoRepository<UserModel, String> {

    UserModel findUserByUserName(String userName);

}
