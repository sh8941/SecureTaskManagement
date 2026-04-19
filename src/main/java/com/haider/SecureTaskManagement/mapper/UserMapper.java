package com.haider.SecureTaskManagement.mapper;

import com.haider.SecureTaskManagement.dto.request.UserRequestDto;
import com.haider.SecureTaskManagement.dto.response.UserResponseDto;
import com.haider.SecureTaskManagement.entity.RoleEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // RequestDto → Entity
    @Mapping(source = "roleId", target = "role.roleId")
    UserEntity toEntity(UserRequestDto dto);

    // Entity → ResponseDto
    @Mapping(source = "role.roleName", target = "roleName")
    UserResponseDto toDto(UserEntity entity);

    // Helper method for Role mapping
    default RoleEntity map(Long roleId) {
        if (roleId == null) return null;
        RoleEntity role = new RoleEntity();
        role.setRoleId(roleId);
        return role;
    }
}
