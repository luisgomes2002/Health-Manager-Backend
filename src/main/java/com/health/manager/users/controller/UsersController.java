package com.health.manager.users.controller;

import com.health.manager.users.response.user.UserDetailsResponse;
import com.health.manager.users.response.user.UsersResponse;
import com.health.manager.users.service.FindUserDetailsService;
import com.health.manager.users.service.FindUsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final FindUsersService findUsersService;
    private final FindUserDetailsService findUserDetailsService;

    public UsersController(FindUsersService findUsersService, FindUserDetailsService findUserDetailsService) {
        this.findUsersService = findUsersService;
        this.findUserDetailsService = findUserDetailsService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsersResponse> getAllUsers(Pageable pageable) {
        return findUsersService.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailsResponse getUserDetails(@PathVariable UUID id) {
        return findUserDetailsService.getUserDetails(id);
    }
}
