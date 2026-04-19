package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
    Optional<RoleEntity> findByRoleId(Long roleId);
}
