package com.example.basicApp.repo;

import com.example.basicApp.entity.AddressEntity;
import com.example.basicApp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
   List<AddressEntity> findAllByUserDetails(UserEntity user);
}
