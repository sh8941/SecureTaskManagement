package com.haider.SecureTaskManagement.mapper;

import com.haider.SecureTaskManagement.dto.request.TaskRequestDto;
import com.haider.SecureTaskManagement.dto.response.TaskResponseDto;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // ENTITY to RESPONSE DTO
    @Mapping(source = "createdBy.userId", target = "createdById")
    @Mapping(source = "assignedTo.userId", target = "assignedToId")
    TaskResponseDto toResponseDto(TaskEntity entity);


    //  REQUEST DTO to ENTITY
    // Ignore fields that are set manually in service
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    TaskEntity toEntity(TaskRequestDto dto);


    // UPDATE (VERY IMPORTANT - INTERVIEW LEVEL)
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntityFromDto(TaskRequestDto dto, @MappingTarget TaskEntity entity);
}
