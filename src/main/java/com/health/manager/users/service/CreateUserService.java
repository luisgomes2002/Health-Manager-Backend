package com.health.manager.users.service;

import com.health.manager.users.entity.Users;
import com.health.manager.users.mapper.user.CreateUserMapper;
import com.health.manager.users.repository.UsersRepository;
import com.health.manager.users.request.CreateUserRequest;
import com.health.manager.users.response.user.UsersResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UsersRepository usersRepository;
    private final CreateUserMapper createUserMapper;

    public CreateUserService(UsersRepository usersRepository, CreateUserMapper createUserMapper) {
        this.usersRepository = usersRepository;
        this.createUserMapper = createUserMapper;
    }

    public UsersResponse createUser(CreateUserRequest createUserRequest) {
        Users savedUser = usersRepository.save(createUserMapper.toEntity(createUserRequest));
        return createUserMapper.toResponse(savedUser);
    }
}
