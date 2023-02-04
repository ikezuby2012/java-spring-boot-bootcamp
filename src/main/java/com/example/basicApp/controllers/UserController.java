package com.example.basicApp.controllers;

import com.example.basicApp.DTO.AddressDTO;
import com.example.basicApp.DTO.UserDto;
import com.example.basicApp.Service.AddressService;
import com.example.basicApp.Service.UserService;
import com.example.basicApp.Utils.UserServiceException;
import com.example.basicApp.model.Request.UserRequestModel;
import com.example.basicApp.model.Response.AddressRest;
import com.example.basicApp.model.Response.ErrorMessage;
import com.example.basicApp.model.Response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRequestModel getUser(@PathVariable String id) {
        UserRequestModel newUser = new UserRequestModel();
        UserDto userDto = userService.getUserById(id);
        BeanUtils.copyProperties(userDto, newUser);
        return newUser;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRequestModel createUser(@RequestBody UserRequestModel userDetails) throws Exception {
        UserRequestModel newUser = new UserRequestModel();

        if (userDetails.getLastName().isEmpty()) throw new UserServiceException(ErrorMessage.MISSING_REQUIRED_FIELD);
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(userDetails, userDto);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
//        BeanUtils.copyProperties(createdUser, newUser);
        newUser = modelMapper.map(createdUser, UserRequestModel.class);
        return newUser;
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRequestModel updateUser(@PathVariable String id, @RequestBody UserRequestModel userDetails) {
        UserRequestModel newUser = new UserRequestModel();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, newUser);
        return newUser;
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName("DELETE");
        userService.deleteUser(id);
        operationStatusModel.setOperationResult("SUCCESS");
        return operationStatusModel;
    }

    //    get all users
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRequestModel> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit) {
        List<UserRequestModel> users = new ArrayList<>();

        List<UserDto> allUsers = userService.getAllUsers(page, limit);

        for (UserDto userDto : allUsers) {
            UserRequestModel userModel = new UserRequestModel();
            BeanUtils.copyProperties(userDto, userModel);
            users.add(userModel);
        }
        return users;
    }

    @GetMapping(path = "/{id}/address", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<AddressRest> getUserAddress(@PathVariable String id) {

        List<AddressRest> returnValue = new ArrayList<>();
        List<AddressDTO> address = addressService.getAddresses(id);

        if (address != null && !address.isEmpty()) {
            Type listType = new TypeToken<List<AddressRest>>() {
            }.getType();
            returnValue = new ModelMapper().map(address, listType);
        }
        return returnValue;
    }

    @GetMapping(path = "verify-email", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel verifyEmailToken (@RequestParam(value = "token") String token) {
        OperationStatusModel returnValue = new OperationStatusModel();

        returnValue.setOperationName("");
        boolean isVerified = userService.verifyEmailToken(token);
        if (isVerified) returnValue.setOperationResult("success");
        else returnValue.setOperationResult("something went wrong!");
        return returnValue;
    }
}
