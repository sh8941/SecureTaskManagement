package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.config.SecurityUtils;
import com.haider.SecureTaskManagement.dto.request.UserRequestDto;
import com.haider.SecureTaskManagement.dto.response.UserResponseDto;
import com.haider.SecureTaskManagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Tag(name = "User Controller", description = "To register the user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityUtils securityUtils;

    @Operation(summary = "To create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added")
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto dto = userService.addUser(userRequestDto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "To fetch current user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details fetched"),
    })
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyDetails(){
        UserResponseDto userResponseDto = userService.getCurrentUser();
        return ResponseEntity.ok(userResponseDto);
    }

}
