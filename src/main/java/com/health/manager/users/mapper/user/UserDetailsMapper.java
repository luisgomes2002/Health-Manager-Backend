package com.health.manager.users.mapper.user;

import com.health.manager.users.entity.Users;
import com.health.manager.users.response.user.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    public Users toEntity(UserDetailsResponse userDetailsResponse);

    public UserDetailsResponse toResponse(Users users);
}
