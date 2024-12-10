package com.thuan.identiy_service.controller;

import com.thuan.identiy_service.dto.request.UserCreationRequest;
import com.thuan.identiy_service.dto.request.UserUpdateRequest;
import com.thuan.identiy_service.entity.User;
import com.thuan.identiy_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody UserCreationRequest request) {
        return userService.create(request);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable(name = "userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable(name = "userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "user deleted";
    }
}
