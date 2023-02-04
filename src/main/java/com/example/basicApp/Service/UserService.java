package com.example.basicApp.Service;

import com.example.basicApp.DTO.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDto createUser (UserDto user);
    UserDto getUser (String email);
    UserDto getUserById(String id);
    UserDto updateUser(String id, UserDto user);
    void deleteUser(String id);
    List<UserDto> getAllUsers(int page, int limit);
   boolean verifyEmailToken(String token);
}
