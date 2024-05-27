package com.myprojects.passwordmanager.service;

import com.myprojects.passwordmanager.dto.UserDto;
import com.myprojects.passwordmanager.exception.UserServiceException;
import com.myprojects.passwordmanager.model.User;
import com.myprojects.passwordmanager.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto) {

        if (Boolean.TRUE.equals(userRepository.existsByEmail(userDto.getEmail()))) {
            throw new UserServiceException("User is already register with email:" + userDto.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

}
