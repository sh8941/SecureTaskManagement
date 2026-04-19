package com.haider.SecureTaskManagement.repo;

import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCommentRepo extends JpaRepository<TaskCommentEntity, Long> {
    Optional<TaskCommentEntity> findById(Long aLong);
    TaskCommentEntity save(TaskCommentEntity taskCommentEntity);
}
