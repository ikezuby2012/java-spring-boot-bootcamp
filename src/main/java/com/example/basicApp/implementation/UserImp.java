package com.example.basicApp.implementation;

import com.example.basicApp.DTO.UserDto;
import com.example.basicApp.Service.UserService;
import com.example.basicApp.UserRepository;
import com.example.basicApp.Utils.Utils;
import com.example.basicApp.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("email already exist!");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity newUser = userRepository.save(userEntity);

        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(newUser,returnUser);
        return returnUser;
    }
}
