package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.TaskRequestDto;
import com.haider.SecureTaskManagement.dto.response.TaskResponseDto;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.exception.AccessDeniedException;
import com.haider.SecureTaskManagement.exception.ResourceNotFoundException;
import com.haider.SecureTaskManagement.mapper.TaskMapper;
import com.haider.SecureTaskManagement.repo.TaskRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserService userService;

    @Mock
    private SecurityUtils securityUtils;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    private UserEntity user;
    private TaskEntity task;

    @BeforeEach
    void setup() {
        user = new UserEntity();
        user.setUserId(1L);

        task = new TaskEntity();
        task.setTaskId(1L);
        task.setCreatedBy(user);
    }

    @Test
    void testGetTaskEntity_success_owner() {
        when(securityUtils.getCurrentUser()).thenReturn(user);
        when(taskRepo.findTaskByTaskId(1L)).thenReturn(Optional.of(task));

        TaskEntity result = taskService.getTaskEntity(1L);

        assertNotNull(result);
        assertEquals(1L, result.getTaskId());
    }

    @Test
    void testGetTaskEntity_notFound() {
        when(securityUtils.getCurrentUser()).thenReturn(user);
        when(taskRepo.findTaskByTaskId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> taskService.getTaskEntity(1L));
    }

    @Disabled
    @Test
    void testGetTaskEntity_accessDenied() {
        UserEntity anotherUser = new UserEntity();
        anotherUser.setUserId(2L);

        when(securityUtils.getCurrentUser()).thenReturn(anotherUser);
        when(taskRepo.findTaskByTaskId(1L)).thenReturn(Optional.of(task));

        assertThrows(AccessDeniedException.class,
                () -> taskService.getTaskEntity(1L));
    }

    @Test
    void testAddTask_success() {
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setAssignedToId(2L);

        UserEntity assignedUser = new UserEntity();
        assignedUser.setUserId(2L);

        TaskEntity entity = new TaskEntity();
        TaskResponseDto responseDto = new TaskResponseDto();

        when(taskMapper.toEntity(requestDto)).thenReturn(entity);
        when(securityUtils.getCurrentUser()).thenReturn(user);
        when(userService.getUserById(2L)).thenReturn(assignedUser);
        when(taskRepo.save(any(TaskEntity.class))).thenReturn(entity);
        when(taskMapper.toResponseDto(entity)).thenReturn(responseDto);

        TaskResponseDto result = taskService.addTask(requestDto);

        assertNotNull(result);
        verify(taskRepo, times(1)).save(any(TaskEntity.class));
    }


    @Test
    void testDeleteTask() {
        TaskResponseDto dto = new TaskResponseDto();

        when(securityUtils.getCurrentUser()).thenReturn(user);
        when(taskRepo.findTaskByTaskId(1L)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(TaskEntity.class))).thenReturn(task);
        when(taskMapper.toResponseDto(task)).thenReturn(dto);

        TaskResponseDto result = taskService.deleteTask(1L);

        assertNotNull(result);
        assertTrue(task.isDeleted());
    }
}