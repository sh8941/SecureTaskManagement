package com.haider.SecureTaskManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    // user who performed action
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String action;

    private String entityType;

    private Long entityId;

    private LocalDateTime timestamp;

    public AuditLogEntity() {}

    public Long getLogId() {
        return logId;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public String getEntityType() {
        return entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
