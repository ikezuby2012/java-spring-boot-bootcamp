package com.example.basicApp.repo;

import com.example.basicApp.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<UserEntity, Long> {
}
