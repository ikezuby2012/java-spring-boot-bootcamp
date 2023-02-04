package com.example.basicApp.Service;

import com.example.basicApp.DTO.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
   List<AddressDTO> getAddresses(String userId);
}
