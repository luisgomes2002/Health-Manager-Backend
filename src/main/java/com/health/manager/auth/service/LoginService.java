package com.health.manager.auth.service;

import com.health.manager.auth.dto.LoginRequest;
import com.health.manager.auth.dto.LoginResponse;
import com.health.manager.shared.config.TokenService;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {

    private final UsersRepository usersRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(UsersRepository usersRepository, TokenService tokenService, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        var user = usersRepository.findByEmail(request.email())
                .filter(u -> u.isActive())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        String token = tokenService.generateToken(user);
        return new LoginResponse(token);
    }
}
