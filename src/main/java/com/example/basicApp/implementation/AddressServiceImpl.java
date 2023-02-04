package com.example.basicApp.implementation;

import com.example.basicApp.DTO.AddressDTO;
import com.example.basicApp.Service.AddressService;
import com.example.basicApp.entity.AddressEntity;
import com.example.basicApp.entity.UserEntity;
import com.example.basicApp.repo.AddressRepository;
import com.example.basicApp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
   @Autowired
   UserRepository userRepository;

   @Autowired
   AddressRepository addressRepository;

   @Override
   public List<AddressDTO> getAddresses(String userId) {
      List<AddressDTO> returnValue = new ArrayList<>();
      UserEntity userEntity = userRepository.findByUserId(userId);
      if (userEntity == null) return returnValue;
      Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
      for (AddressEntity addressEntity:addresses) {
         returnValue.add(new ModelMapper().map(addressEntity, AddressDTO.class));
      }
      return returnValue;
   }
}
