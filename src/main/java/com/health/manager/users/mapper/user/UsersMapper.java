package com.health.manager.users.mapper.user;

import com.health.manager.users.entity.Users;
import com.health.manager.users.dto.response.user.UsersResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    public Users toEntity(UsersResponse usersResponse);

    public UsersResponse toResponse(Users users);

}
