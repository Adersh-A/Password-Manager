package com.myprojects.passwordmanager.exception;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable e) {
        super(message, e);
    }
}
