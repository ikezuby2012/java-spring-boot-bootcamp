package com.example.basicApp.controllers;

import com.example.basicApp.DTO.UserDto;
import com.example.basicApp.Service.UserService;
import com.example.basicApp.model.Request.UserRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "get user method was called";
    }

    @PostMapping
    public UserRequestModel createUser(@RequestBody UserRequestModel userDetails) {
        UserRequestModel newUser = new UserRequestModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, newUser);


        return newUser;
    }

}
