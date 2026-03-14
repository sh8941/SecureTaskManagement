package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
