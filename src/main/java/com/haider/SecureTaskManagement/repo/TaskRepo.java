package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {
    public Optional<TaskEntity> findTaskByTaskId(Long id);
    public TaskEntity save(TaskEntity task);
    public Page<TaskEntity> findByCreatedBy(UserEntity user, Pageable pageable);
    public Page<TaskEntity> findByAssignedTo(UserEntity user, Pageable pageable);
}
