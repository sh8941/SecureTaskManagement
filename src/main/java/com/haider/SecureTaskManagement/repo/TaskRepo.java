package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    public Optional<TaskEntity> getTaskByTaskId(Long id);
    public TaskEntity save(TaskEntity task);
}
