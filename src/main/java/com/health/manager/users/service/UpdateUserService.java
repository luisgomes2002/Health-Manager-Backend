package com.health.manager.users.service;

import com.health.manager.users.dto.request.UpdateUserRequest;
import com.health.manager.users.dto.response.user.UsersResponse;
import com.health.manager.users.mapper.user.UsersMapper;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateUserService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UpdateUserService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Transactional
    public UsersResponse updateUser(UUID id, UpdateUserRequest request) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (request.getName() != null) user.setName(request.getName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());

        return usersMapper.toResponse(usersRepository.save(user));
    }
}
