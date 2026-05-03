package com.health.manager.auth.controller;

import com.health.manager.auth.dto.LoginRequest;
import com.health.manager.auth.dto.LoginResponse;
import com.health.manager.auth.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }
}
