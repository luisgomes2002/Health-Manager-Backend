package com.health.manager.users.mapper.user;

import com.health.manager.users.entity.Users;
import com.health.manager.users.request.CreateUserRequest;
import com.health.manager.users.response.user.UsersResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {

    Users toEntity(CreateUserRequest createUserRequest);

    UsersResponse toResponse(Users users);

    CreateUserRequest toRequest(Users users);
}
