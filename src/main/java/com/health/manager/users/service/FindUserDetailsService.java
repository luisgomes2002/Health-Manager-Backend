package com.health.manager.users.service;

import com.health.manager.users.mapper.user.UserDetailsMapper;
import com.health.manager.users.repository.UsersRepository;
import com.health.manager.users.response.user.UserDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FindUserDetailsService {

    private final UsersRepository usersRepository;
    private final UserDetailsMapper usersMapper;

    public FindUserDetailsService(UsersRepository usersRepository, UserDetailsMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Transactional(readOnly = true)
    public UserDetailsResponse getUserDetails(UUID id) {
        return usersRepository.findDetailedById(id)
                .map(usersMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
