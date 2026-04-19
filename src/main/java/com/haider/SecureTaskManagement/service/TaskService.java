package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.TaskRequestDto;
import com.haider.SecureTaskManagement.dto.response.PageResponse;
import com.haider.SecureTaskManagement.dto.response.TaskResponseDto;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import com.haider.SecureTaskManagement.exception.AccessDeniedException;
import com.haider.SecureTaskManagement.exception.ResourceNotFoundException;
import com.haider.SecureTaskManagement.mapper.TaskMapper;
import com.haider.SecureTaskManagement.repo.TaskRepo;
import com.haider.SecureTaskManagement.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private TaskMapper taskMapper;


    public TaskEntity getTaskEntity(Long id) {
        UserEntity currentUser = securityUtils.getCurrentUser();

        TaskEntity task = taskRepo.findTaskByTaskId(id).orElseThrow(() ->
                new ResourceNotFoundException("Task with id " + id + " not found"));

        if (isOwner(task,currentUser) || isAdmin(currentUser)){
            return task;
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    private boolean isAdmin(UserEntity currentUser) {
        return currentUser.getRole().getRoleName().equals("ADMIN");
    }

    private boolean isOwner(TaskEntity task, UserEntity currentUser) {
        return task.getCreatedBy().getUserId().equals(currentUser.getUserId());
    }

    public TaskResponseDto addTask(TaskRequestDto requestDto) {
        TaskEntity taskEntity = taskMapper.toEntity(requestDto);

        // fetch current user
        UserEntity createdBy = securityUtils.getCurrentUser();

        // fetch assigned user
        UserEntity assignedTo = userService.getUserById(requestDto.getAssignedToId());

        taskEntity.setCreatedBy(createdBy);
        taskEntity.setAssignedTo(assignedTo);
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setDueDate(LocalDateTime.now().plusDays(10));
        taskEntity.setUpdatedAt(LocalDateTime.now());

        taskEntity = taskRepo.save(taskEntity);

        return taskMapper.toResponseDto(taskEntity);
    }

    @Cacheable(value = "tasks", key = "#page + '-' + #size + '-' + #status")
    public PageResponse<TaskResponseDto> getMyTask(Status status, Priority priority, Pageable pageable) {
        UserEntity currentUser = securityUtils.getCurrentUser();

        Specification<TaskEntity> specification;

        if (isAdmin(currentUser)) {
            // admin access logic
            specification = TaskSpecification.filterAll(status, priority);
        } else {
            // user access logic
            specification = TaskSpecification.filterMyTasks(currentUser, status, priority);
        }


        Page<TaskEntity> tasks = taskRepo.findAll(specification, pageable);

        List<TaskResponseDto> responseDtoList = tasks.getContent()
                .stream()
                .map(taskMapper::toResponseDto)
                .toList();

        PageResponse<TaskResponseDto> response = new PageResponse<>();
        response.setTotalElements(tasks.getTotalElements());
        response.setTotalPages(tasks.getTotalPages());
        response.setPage(tasks.getNumber());
        response.setSize(tasks.getSize());
        response.setContent(responseDtoList);

        return response;
    }

    public PageResponse<TaskResponseDto> getAssignedToMeTask(Status status, Priority priority, Pageable pageable) {
        UserEntity currentUser = securityUtils.getCurrentUser();

        Specification<TaskEntity> specification = TaskSpecification.filterAssignedTasks(currentUser, status, priority);

        Page<TaskEntity> taskEntityPage = taskRepo.findAll(specification,pageable);

        List<TaskResponseDto> taskResponseDtoList = taskEntityPage.getContent().stream()
                .map(taskMapper::toResponseDto).toList();

        PageResponse<TaskResponseDto> response = new PageResponse<>();
        response.setPage(pageable.getPageNumber());
        response.setSize(pageable.getPageSize());
        response.setTotalElements(taskEntityPage.getTotalElements());
        response.setTotalPages(taskEntityPage.getTotalPages());
        response.setContent(taskResponseDtoList);
        response.setLast(taskEntityPage.isLast());

        return response;
    }

    @Cacheable(value = "tasks", key = "#id")
    public TaskResponseDto getTaskResponseDto(Long id) {
        System.out.println();
        System.out.println("fetching from db....");
        System.out.println();
        TaskEntity taskEntity = getTaskEntity(id);
        return taskMapper.toResponseDto(taskEntity);
    }

    public TaskResponseDto deleteTask(Long id) {
        TaskEntity taskEntity = getTaskEntity(id);
        taskEntity.setDeleted(true);
        taskEntity = taskRepo.save(taskEntity);
        return taskMapper.toResponseDto(taskEntity);
    }
}
