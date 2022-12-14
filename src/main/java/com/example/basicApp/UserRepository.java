package com.example.basicApp;

import com.example.basicApp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
//    UserEntity findUserByEmail(String email);
    UserEntity findByEmail(String email);
}
