package com.haider.SecureTaskManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class UserResponseDto {

    @Schema(description = "Unique ID of the user", example = "313")
    private Long userId;
    @Schema(description = "Username for the user", example = "John_Doe")
    private String username;
    @Schema(description = "Email of the user", example = "user@email.com")
    private String email;
    @Schema(description = "Role for the user", example = "USER")
    private String roleName;
    @Schema(description = "Time of the user creation")
    private LocalDateTime createdAt;

    // getters & setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}