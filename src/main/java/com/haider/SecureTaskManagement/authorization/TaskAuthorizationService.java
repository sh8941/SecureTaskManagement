package com.haider.SecureTaskManagement.authorization;

import com.haider.SecureTaskManagement.entity.TaskCommentEntity;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.enums.Visibility;
import org.springframework.stereotype.Service;

@Service
public class TaskAuthorizationService {
    public boolean isTaskOwned(TaskEntity task, UserEntity user) {
        return task.getCreatedBy().getUserId().equals(user.getUserId());
    }

    public boolean isTaskAssigned(TaskEntity task, UserEntity user) {
        return task.getAssignedTo() != null &&
                task.getAssignedTo().getUserId().equals(user.getUserId());
    }

    public boolean isCommentVisible(TaskCommentEntity comment, UserEntity user) {
        TaskEntity task = comment.getTaskId();

        if (task.getVisibility() == Visibility.PUBLIC) {
            return true;
        }

        return isTaskOwned(task, user) || isTaskAssigned(task, user);
    }

    public boolean canCommentOnTask(TaskEntity task, UserEntity user) {

        if (isAdmin(user)) return true;

        if (task.getVisibility() == Visibility.PUBLIC) return true;

        if (isTaskOwned(task, user)) return true;

        if (isTaskAssigned(task, user)) return true;

        return false;
    }

    private boolean isAdmin(UserEntity user) {
        return user.getRole().getRoleName().equals("ADMIN");
    }
}
