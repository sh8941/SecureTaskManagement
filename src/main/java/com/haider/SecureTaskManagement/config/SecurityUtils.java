package com.haider.SecureTaskManagement.config;

import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    @Autowired
    private UserRepo userRepo;

    public String getCurrentUsername() {
        Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    public UserEntity getCurrentUser() {
        String username = getCurrentUsername();
        UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
}
