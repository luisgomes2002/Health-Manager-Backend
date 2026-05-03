package com.health.manager.users.service;

import com.health.manager.shared.enums.Role;
import com.health.manager.users.entity.Users;
import com.health.manager.users.mapper.user.CreateUserMapper;
import com.health.manager.users.repository.UsersRepository;
import com.health.manager.users.dto.request.CreateUserRequest;
import com.health.manager.users.dto.response.user.UsersResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UsersRepository usersRepository;
    private final CreateUserMapper createUserMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUserService(UsersRepository usersRepository, CreateUserMapper createUserMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.createUserMapper = createUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UsersResponse createUser(CreateUserRequest createUserRequest) {
        Users user = createUserMapper.toEntity(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setRole(Role.CLIENT);
        return createUserMapper.toResponse(usersRepository.save(user));
    }
}
