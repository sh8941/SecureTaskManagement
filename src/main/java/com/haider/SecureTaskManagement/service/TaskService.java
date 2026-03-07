package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.dto.TaskCreateRequestDto;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.repo.TaskRepo;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    public TaskEntity getTask(Long id) {
        return taskRepo.getTaskByTaskId(id).get();
    }

    public TaskEntity addTask(TaskCreateRequestDto dto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setPriority(dto.getPriority());
        taskEntity.setDueDate(dto.getDueDate());
        taskEntity.setStatus(dto.getStatus());
        taskEntity.setVisibility(dto.getVisibility());

        UserEntity assignedUser = userRepo.findByUserId(dto.getAssignedToUserId()).orElseThrow();
        taskEntity.setAssignedTo(assignedUser);

        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setUpdatedAt(LocalDateTime.now());
        taskEntity.setDeleted(false);

        return taskRepo.save(taskEntity);

    }
}
