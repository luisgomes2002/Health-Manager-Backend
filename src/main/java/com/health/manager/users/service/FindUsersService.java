package com.health.manager.users.service;

import com.health.manager.users.mapper.user.UsersMapper;
import com.health.manager.users.repository.UsersRepository;
import com.health.manager.users.response.user.UsersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindUsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public FindUsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public Page<UsersResponse> getAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable)
                .map(users -> usersMapper.toResponse(users));
    }
}
