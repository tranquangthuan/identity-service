package com.thuan.identiy_service.controller;

import com.thuan.identiy_service.dto.request.UserCreationRequest;
import com.thuan.identiy_service.dto.request.UserUpdateRequest;
import com.thuan.identiy_service.dto.response.ApiResponse;
import com.thuan.identiy_service.dto.response.UserResponse;
import com.thuan.identiy_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.create(request));
        return apiResponse;
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(r -> log.info(r.getAuthority()));
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable(name = "userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable(name = "userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "user deleted";
    }

    @GetMapping("/myinfo")
    public ApiResponse<UserResponse> myInfo() {
        return ApiResponse.<UserResponse>builder().result(userService.myInfo()).build();
    }
}
