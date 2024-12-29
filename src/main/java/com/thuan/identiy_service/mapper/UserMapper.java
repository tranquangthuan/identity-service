package com.thuan.identiy_service.mapper;

import com.thuan.identiy_service.dto.request.UserCreationRequest;
import com.thuan.identiy_service.dto.request.UserUpdateRequest;
import com.thuan.identiy_service.dto.response.UserResponse;
import com.thuan.identiy_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
