package com.haider.SecureTaskManagement.specification;

import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<TaskEntity> filterMyTasks(UserEntity user, Status status, Priority priority) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // mandatory: current user
            predicates.add(cb.equal(root.get("createdBy"), user));

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (priority != null) {
                predicates.add(cb.equal(root.get("priority"), priority));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<TaskEntity> filterAssignedTasks(UserEntity user, Status status, Priority priority) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // mandatory: current user
            predicates.add(cb.equal(root.get("assignedTo"), user));

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (priority != null) {
                predicates.add(cb.equal(root.get("priority"), priority));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<TaskEntity> filterAll(Status status, Priority priority) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (priority != null) {
                predicates.add(cb.equal(root.get("priority"), priority));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}