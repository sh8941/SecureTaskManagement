package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    public Optional<TaskEntity> getTaskByTaskId(Long id);
    public TaskEntity save(TaskEntity task);
    public List<TaskEntity> getTasksByCreatedBy(UserEntity userEntity);
}
