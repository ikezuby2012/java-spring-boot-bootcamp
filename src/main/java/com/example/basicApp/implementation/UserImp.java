package com.example.basicApp.implementation;

import com.example.basicApp.DTO.AddressDTO;
import com.example.basicApp.DTO.UserDto;
import com.example.basicApp.Service.UserService;
import com.example.basicApp.repo.UserRepo;
import com.example.basicApp.repo.UserRepository;
import com.example.basicApp.Utils.UserServiceException;
import com.example.basicApp.Utils.Utils;
import com.example.basicApp.entity.UserEntity;
import com.example.basicApp.model.Response.ErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class UserImp implements UserService {
   @Autowired
   UserRepository userRepository;

   @Autowired
   UserRepo userRepo;

   @Autowired
   Utils utils;

   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public UserDto createUser(UserDto user) {
      if (userRepository.findByEmail(user.getEmail()) != null)
         throw new RuntimeException("email already exist!");

      for (int i = 0; i < user.getAddress().size(); i++) {
         AddressDTO address = user.getAddress().get(i);
         address.setUserDetails(user);
         address.setAddressId(utils.generateAddressId(30));
         user.getAddress().set(i, address);
      }
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(user, userEntity);
      ModelMapper modelMapper = new ModelMapper();
      UserEntity userEntity = modelMapper.map(user, UserEntity.class);

      String publicUserId = utils.generateUserId(30);
      userEntity.setUserId(publicUserId);
      userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));

      UserEntity newUser = userRepository.save(userEntity);

      UserDto returnUser = modelMapper.map(newUser, UserDto.class);
      return returnUser;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      UserEntity userEntity = userRepository.findByEmail(email);
      if (userEntity == null) throw new UsernameNotFoundException(email);

      //every instance in this guy must return true
      return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), userEntity.getEmailVerificationStatus(), true, true,
          true, new ArrayList<>());
//        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
   }

   @Override
   public UserDto getUser(String email) {
      UserEntity userEntity = userRepository.findByEmail(email);

      if (userEntity == null) throw new UsernameNotFoundException(email);

      UserDto returnUser = new UserDto();
      BeanUtils.copyProperties(userEntity, returnUser);
      return returnUser;
   }

   @Override
   public UserDto getUserById(String id) {
      UserDto userDto = new UserDto();
      UserEntity userEntity = userRepository.findByUserId(id);

      if (userEntity == null) throw new UsernameNotFoundException(id);
      BeanUtils.copyProperties(userEntity, userDto);
      return null;
   }

   @Override
   public UserDto updateUser(String id, UserDto user) {
      UserDto userDto = new UserDto();
      UserEntity userEntity = userRepository.findByUserId(id);

      if (userEntity == null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND);
      userEntity.setFirstName(user.getFirstName());
      userEntity.setLastName(user.getLastName());

      userRepository.save(userEntity);
      BeanUtils.copyProperties(userEntity, userDto);
      return userDto;
   }

   @Transactional
   @Override
   public void deleteUser(String id) {
      UserEntity userEntity = userRepository.findByUserId(id);

      if (userEntity == null)
         throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND);
      userRepository.delete(userEntity);
   }

   @Override
   public List<UserDto> getAllUsers(int page, int limit) {
      List<UserDto> returnUsers = new ArrayList<>();
      if (page > 0) page -= 1;
      Pageable pageable = PageRequest.of(page, limit);
      Page<UserEntity> userPage = userRepo.findAll(pageable);
      List<UserEntity> users = userPage.getContent();

      for (UserEntity userEntity : users) {
         UserDto userDto = new UserDto();
         BeanUtils.copyProperties(userEntity, userDto);
         returnUsers.add(userDto);
      }
      return returnUsers;
   }

   @Override
   public boolean verifyEmailToken(String token) {
      boolean returnValue = false;

      //find user by token
      UserEntity userEntity = userRepository.findByEmailVerificationToken(token);

      if (userEntity != null) {
         boolean hasTokenExpired = Utils.hasTokenExpired(token);
         if (!hasTokenExpired) {
            userEntity.setEmailVerificationToken(null);
            userEntity.setEmailVerificationStatus(Boolean.TRUE);
            userRepository.save(userEntity);
            returnValue = true;
         }
      }
      return returnValue;
   }
}
