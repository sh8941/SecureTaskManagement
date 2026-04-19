package com.haider.SecureTaskManagement.mapper;

import com.haider.SecureTaskManagement.dto.request.TaskCommentRequestDto;
import com.haider.SecureTaskManagement.dto.response.TaskCommentResponseDto;
import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

    // Entity to Response DTO
    @Mapping(source = "taskId.taskId", target = "taskId")
    @Mapping(source = "userId.userId", target = "userId")
    TaskCommentResponseDto toDto(TaskCommentEntity entity);

    // Request DTO to Entity (partial)
    @Mapping(target = "taskId", ignore = true)       // will set manually
    @Mapping(target = "userId", ignore = true)       // will set manually
    @Mapping(target = "taskCommentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TaskCommentEntity toEntity(TaskCommentRequestDto dto);
}