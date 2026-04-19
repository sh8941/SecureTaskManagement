package com.haider.SecureTaskManagement.authorization;

import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.enums.Visibility;
import org.springframework.stereotype.Service;

@Service
public class TaskAuthorization {

    public boolean canView(TaskEntity task, UserEntity user) {

        if (isAdmin(user)) return true;

        if (task.getVisibility() == Visibility.PUBLIC) return true;

        if (isOwner(task, user)) return true;

        if (isAssigned(task, user)) return true;

        return false;
    }

    public boolean canEdit(TaskEntity task, UserEntity user) {

        if (isAdmin(user)) return true;

        return isOwner(task, user);
    }

    // helpers
    private boolean isOwner(TaskEntity task, UserEntity user) {
        return task.getCreatedBy().getUserId().equals(user.getUserId());
    }

    private boolean isAssigned(TaskEntity task, UserEntity user) {
        return task.getAssignedTo() != null && task.getAssignedTo().getUserId().equals(user.getUserId());
    }

    private boolean isAdmin(UserEntity user) {
        return user.getRole().getRoleName().equals("ADMIN");
    }
}