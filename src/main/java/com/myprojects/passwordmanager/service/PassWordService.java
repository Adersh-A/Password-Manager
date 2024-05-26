package com.myprojects.passwordmanager.service;

import com.myprojects.passwordmanager.dto.PasswordDto;
import com.myprojects.passwordmanager.model.PassWord;
import com.myprojects.passwordmanager.model.User;
import com.myprojects.passwordmanager.model.UserModel;
import com.myprojects.passwordmanager.repository.PassWordRepository;
import com.myprojects.passwordmanager.repository.UserRepository;
import com.myprojects.passwordmanager.util.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PassWordService {

    private final PassWordRepository passWordRepository;
    private final UserRepository userRepository;
    public PassWordService(PassWordRepository passWordRepository,
                           UserRepository userRepository){
        this.passWordRepository = passWordRepository;
        this.userRepository = userRepository;
    }

    public void savePassWord(PasswordDto passwordDto){
        UserModel userModel = new UserModel();
        userModel.setUserName(getLoggedInUser().getEmail());
        UserModel userDetails = passWordRepository.findUserByUserName(userModel.getUserName());

        if(Objects.nonNull(userDetails)){
            log.info("user entry already exists, adding password to list");
            boolean isAppNameAlreadyExists = userDetails.getPassWordList().stream().
                    anyMatch(passWord -> passWord.getAppName().equals(passwordDto.getAppName()));
            if(isAppNameAlreadyExists){
                throw new RuntimeException("entry already exists, pls update ");
            }
            userDetails.getPassWordList().add(getPassWord(passwordDto));
            userModel = userDetails;
        }else {
            log.info("no passwords for user");
            userModel.setPassWordList(List.of(getPassWord(passwordDto)));

        }

        passWordRepository.save(userModel);
    }

    private static PassWord getPassWord(PasswordDto passwordDto) {
        return PassWord.builder().
                appName(passwordDto.getAppName()).
                passWord(EncryptionUtils.encrypt(passwordDto.getPassWord())).
                createdTimestamp(Timestamp.from(Instant.now())).
                updatedTimestamp(Timestamp.from(Instant.now())).
                build();
    }


    public PasswordDto getPassWord(String appName){
        UserModel userModel = passWordRepository.findUserByUserName(getLoggedInUser().getEmail());
        Optional<PassWord> passWord = userModel.getPassWordList().stream().filter(pw -> pw.getAppName().equals(appName)).findAny();
        if(passWord.isPresent()){
            return PasswordDto.builder().
                    appName(appName).
                    passWord(EncryptionUtils.decrypt(passWord.get().getPassWord())).
                    build();
        }else {
            throw new RuntimeException("app pass word not found!!");
        }
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
    }
}
