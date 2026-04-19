package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.authorization.CommentAuthorization;
import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.TaskCommentRequestDto;
import com.haider.SecureTaskManagement.dto.response.TaskCommentResponseDto;
import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.exception.AccessDeniedException;
import com.haider.SecureTaskManagement.exception.ResourceNotFoundException;
import com.haider.SecureTaskManagement.mapper.TaskCommentMapper;
import com.haider.SecureTaskManagement.repo.TaskCommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskCommentService {
    @Autowired
    private TaskCommentRepo taskCommentRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskCommentMapper taskCommentMapper;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private CommentAuthorization commentAuthorization;

    public TaskCommentResponseDto addCommentEntity(TaskCommentRequestDto taskCommentRequestDto) {

        TaskCommentEntity taskCommentEntity = taskCommentMapper.toEntity(taskCommentRequestDto);

        // fetching entities
        TaskEntity taskEntity = taskService.getTaskEntity(taskCommentRequestDto.getTaskId());
        UserEntity userEntity = securityUtils.getCurrentUser();

        if (! commentAuthorization.canCreate(taskEntity, userEntity)) {
            throw new AccessDeniedException("Unauthorized comment");
        }

        taskCommentEntity.setCreatedAt(LocalDateTime.now());
        taskCommentEntity.setUserId(userEntity);
        taskCommentEntity.setTaskId(taskEntity);

        TaskCommentEntity saved = taskCommentRepo.save(taskCommentEntity);

        return taskCommentMapper.toDto(saved);
    }

    public TaskCommentResponseDto getComment(Long id) {
        TaskCommentEntity taskCommentEntity = taskCommentRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("TaskComment not found with id " + id));

        UserEntity userEntity = securityUtils.getCurrentUser();

        if (! commentAuthorization.canView(taskCommentEntity,userEntity)){
            throw new AccessDeniedException("Unauthorized comment");
        }

        return taskCommentMapper.toDto(taskCommentEntity);
    }

    public TaskCommentEntity getTaskCommentEntity(Long id) {
        return taskCommentRepo.findById(id).orElseThrow(() ->
                new RuntimeException("TaskComment not found with id " + id));
    }



    public TaskCommentResponseDto deleteComment(Long id) {
        TaskCommentEntity taskCommentEntity = getTaskCommentEntity(id);
        taskCommentEntity.setDeleted(true);
        taskCommentEntity = taskCommentRepo.save(taskCommentEntity);
        return taskCommentMapper.toDto(taskCommentEntity);
    }
}
