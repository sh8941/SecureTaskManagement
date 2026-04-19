package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.UserRequestDto;
import com.haider.SecureTaskManagement.dto.response.UserResponseDto;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.mapper.UserMapper;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = userMapper.toEntity(userRequestDto);
        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());

        return userMapper.toDto(userRepo.save(userEntity));
    }

    public UserEntity getUserById(long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id " + id)
        );
    }

    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username " + username));
    }


    public UserResponseDto getCurrentUser(){
        String username = securityUtils.getCurrentUsername();
        UserEntity userEntity = userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username " + username));
        return userMapper.toDto(userEntity);
    }
}