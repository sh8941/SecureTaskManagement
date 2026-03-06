package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

}
