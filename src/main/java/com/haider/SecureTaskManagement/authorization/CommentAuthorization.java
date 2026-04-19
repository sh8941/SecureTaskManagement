package com.haider.SecureTaskManagement.authorization;

import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentAuthorization {

    @Autowired
    private TaskAuthorization taskAuth;

    public boolean canCreate(TaskEntity task, UserEntity user) {
        // reuse task rules
        return taskAuth.canView(task, user);
    }

    public boolean canView(TaskCommentEntity comment, UserEntity user) {
        return taskAuth.canView(comment.getTaskId(), user);
    }

    public boolean canDelete(TaskCommentEntity comment, UserEntity user) {

        if (isAdmin(user)) return true;

        // comment owner can delete
        if (comment.getUserId().getUserId().equals(user.getUserId())) return true;

        // task owner can delete
        if (comment.getTaskId().getCreatedBy().getUserId().equals(user.getUserId())) return true;

        return false;
    }

    private boolean isAdmin(UserEntity user) {
        return user.getRole().getRoleName().equals("ADMIN");
    }
}