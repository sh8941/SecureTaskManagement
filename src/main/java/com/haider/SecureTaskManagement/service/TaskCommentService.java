package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.dto.TaskCommentDto;
import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import com.haider.SecureTaskManagement.repo.TaskCommentRepo;
import com.haider.SecureTaskManagement.repo.TaskRepo;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskCommentService {
    @Autowired
    private TaskCommentRepo taskCommentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;

    public TaskCommentDto addCommentEntity(TaskCommentDto taskCommentDto) {
        TaskCommentEntity taskCommentEntity = new TaskCommentEntity();

        taskCommentEntity.setComment(taskCommentDto.getComment());
        taskCommentEntity.setUserId(userRepo.findById(taskCommentDto.getUserId()).get());

        taskCommentEntity.setTaskId(taskRepo.findById(taskCommentDto.getTaskId()).get());

        taskCommentEntity.setCreatedAt(LocalDateTime.now());

        taskCommentRepo.save(taskCommentEntity);

        return taskCommentDto;
    }

    public TaskCommentDto getComment(Long id) {
        TaskCommentEntity taskCommentEntity = taskCommentRepo.findById(id).get();
        TaskCommentDto taskCommentDto = new TaskCommentDto();

        taskCommentDto.setComment(taskCommentEntity.getComment());
        taskCommentDto.setTaskId(taskCommentEntity.getTaskId().getTaskId());
        taskCommentDto.setUserId(taskCommentEntity.getUserId().getUserId());

        return taskCommentDto;
    }
}
