package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUserId(Long userId);
    UserEntity save(UserEntity userEntity);
}
