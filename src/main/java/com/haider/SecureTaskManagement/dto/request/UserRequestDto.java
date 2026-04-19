package com.haider.SecureTaskManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Request object for creating a user")
public class UserRequestDto {

    @Schema(
            description = "Username for the user",
            example = "john_doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "Username can only contain letters, numbers, dot and underscore")
    private String username;

    @Schema(
            description = "Password for the user",
            example = "********",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @Schema(
            description = "Email for the user",
            example = "user@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Schema(
            description = "Role_id for the user",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Role ID is required")
    @Positive(message = "Role ID must be a positive number")
    private Long roleId;

    // getters & setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}