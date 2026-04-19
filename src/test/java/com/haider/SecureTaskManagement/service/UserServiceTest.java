package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.UserRequestDto;
import com.haider.SecureTaskManagement.dto.response.UserResponseDto;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.mapper.UserMapper;
import com.haider.SecureTaskManagement.repo.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private SecurityUtils securityUtils;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;

    @BeforeEach
    void setup() {
        userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setUsername("testUser");
        userEntity.setPassword("encodedPassword");
    }

    @Test
    void testAddUser_success() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setPassword("plainPassword");

        UserResponseDto responseDto = new UserResponseDto();

        when(userMapper.toEntity(requestDto)).thenReturn(userEntity);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userRepo.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toDto(userEntity)).thenReturn(responseDto);

        UserResponseDto result = userService.addUser(requestDto);

        assertNotNull(result);
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepo).save(any(UserEntity.class));
    }

    @Test
    void testGetUserById_success() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.getUserById(1L));
    }

    @Test
    void testGetByUsername_success() {
        when(userRepo.findByUsername("testUser")).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void testGetByUsername_notFound() {
        when(userRepo.findByUsername("testUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.getByUsername("testUser"));
    }

    @Test
    void testGetCurrentUser_success() {
        UserResponseDto responseDto = new UserResponseDto();

        when(securityUtils.getCurrentUsername()).thenReturn("testUser");
        when(userRepo.findByUsername("testUser")).thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(responseDto);

        UserResponseDto result = userService.getCurrentUser();

        assertNotNull(result);
        verify(securityUtils).getCurrentUsername();
    }

    @Test
    void testGetCurrentUser_notFound() {
        when(securityUtils.getCurrentUsername()).thenReturn("testUser");
        when(userRepo.findByUsername("testUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.getCurrentUser());
    }
}