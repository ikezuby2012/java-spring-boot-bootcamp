package com.example.basicApp.Service;

import com.example.basicApp.DTO.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createUser (UserDto user);
}
