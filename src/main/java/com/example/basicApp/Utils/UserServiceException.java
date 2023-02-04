package com.example.basicApp.Utils;

import com.example.basicApp.model.Response.ErrorMessage;

public class UserServiceException extends RuntimeException{
    private static final long serialVersionUID = 134877110917143567L;
    public UserServiceException(ErrorMessage message) {
        super(String.valueOf(message));
    }
}
