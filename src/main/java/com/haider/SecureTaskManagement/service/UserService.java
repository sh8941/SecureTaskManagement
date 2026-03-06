package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserEntity addUser(UserEntity user) {
        user.setCreatedAt(LocalDateTime.now());
        UserEntity saved = userRepo.save(user);
        return saved;
    }

    public UserEntity getUser(long id) {
        return userRepo.findById(id).get();
    }
}
