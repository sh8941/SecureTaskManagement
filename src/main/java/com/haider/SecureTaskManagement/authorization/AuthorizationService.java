package com.haider.SecureTaskManagement.authorization;

import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private TaskAuthorization taskAuth;
    @Autowired
    private CommentAuthorization commentAuth;

    public boolean canViewTask(TaskEntity task, UserEntity user) {
        return taskAuth.canView(task, user);
    }

    public boolean canEditTask(TaskEntity task, UserEntity user) {
        return taskAuth.canEdit(task, user);
    }

    public boolean canCommentOnTask(TaskEntity task, UserEntity user) {
        return commentAuth.canCreate(task, user);
    }

    public boolean canViewComment(TaskCommentEntity comment, UserEntity user) {
        return commentAuth.canView(comment, user);
    }
}