package com.health.manager.users.controller;

import com.health.manager.users.dto.request.*;
import com.health.manager.users.dto.response.user.*;
import com.health.manager.users.service.*;
import jakarta.validation.Valid;
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
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;
    private final ChangePasswordService changePasswordService;
    private final AssignClientProfileService assignClientProfileService;
    private final AssignProfessionalProfileService assignProfessionalProfileService;
    private final UpdateProfessionalService updateProfessionalService;
    private final RegisterClientService registerClientService;
    private final AddClientSocialMediaService addClientSocialMediaService;
    private final AddProfessionalSocialMediaService addProfessionalSocialMediaService;
    private final DeleteSocialMediaService deleteSocialMediaService;

    public UsersController(FindUsersService findUsersService,
                           FindUserDetailsService findUserDetailsService,
                           CreateUserService createUserService,
                           UpdateUserService updateUserService,
                           DeleteUserService deleteUserService,
                           ChangePasswordService changePasswordService,
                           AssignClientProfileService assignClientProfileService,
                           AssignProfessionalProfileService assignProfessionalProfileService,
                           UpdateProfessionalService updateProfessionalService,
                           RegisterClientService registerClientService,
                           AddClientSocialMediaService addClientSocialMediaService,
                           AddProfessionalSocialMediaService addProfessionalSocialMediaService,
                           DeleteSocialMediaService deleteSocialMediaService) {
        this.findUsersService = findUsersService;
        this.findUserDetailsService = findUserDetailsService;
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
        this.changePasswordService = changePasswordService;
        this.assignClientProfileService = assignClientProfileService;
        this.assignProfessionalProfileService = assignProfessionalProfileService;
        this.updateProfessionalService = updateProfessionalService;
        this.registerClientService = registerClientService;
        this.addClientSocialMediaService = addClientSocialMediaService;
        this.addProfessionalSocialMediaService = addProfessionalSocialMediaService;
        this.deleteSocialMediaService = deleteSocialMediaService;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return createUserService.createUser(createUserRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsersResponse updateUser(@PathVariable UUID id, @RequestBody @Valid UpdateUserRequest request) {
        return updateUserService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        deleteUserService.deleteUser(id);
    }

    @PatchMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable UUID id, @RequestBody @Valid ChangePasswordRequest request) {
        changePasswordService.changePassword(id, request);
    }

    @PostMapping("/{id}/client")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailsResponse assignClientProfile(@PathVariable UUID id,
                                                   @RequestBody AssignClientProfileRequest request) {
        return assignClientProfileService.assignClientProfile(id, request);
    }

    @PostMapping("/{id}/professional")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalResponse assignProfessionalProfile(@PathVariable UUID id,
                                                          @RequestBody @Valid AssignProfessionalProfileRequest request) {
        return assignProfessionalProfileService.assignProfessionalProfile(id, request);
    }

    @PutMapping("/{id}/professional")
    @ResponseStatus(HttpStatus.OK)
    public ProfessionalResponse updateProfessional(@PathVariable UUID id,
                                                   @RequestBody UpdateProfessionalRequest request) {
        return updateProfessionalService.updateProfessional(id, request);
    }

    @PostMapping("/{professionalId}/register-client")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterClientResponse registerClient(@PathVariable UUID professionalId,
                                                 @RequestBody @Valid RegisterClientRequest request) {
        return registerClientService.registerClient(professionalId, request);
    }

    @PostMapping("/{id}/client/social-media")
    @ResponseStatus(HttpStatus.CREATED)
    public SocialMediaResponse addClientSocialMedia(@PathVariable UUID id,
                                                    @RequestBody @Valid AddSocialMediaRequest request) {
        return addClientSocialMediaService.addClientSocialMedia(id, request);
    }

    @PostMapping("/{id}/professional/social-media")
    @ResponseStatus(HttpStatus.CREATED)
    public SocialMediaResponse addProfessionalSocialMedia(@PathVariable UUID id,
                                                          @RequestBody @Valid AddSocialMediaRequest request) {
        return addProfessionalSocialMediaService.addProfessionalSocialMedia(id, request);
    }

    @DeleteMapping("/social-media/{socialMediaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSocialMedia(@PathVariable UUID socialMediaId) {
        deleteSocialMediaService.deleteSocialMedia(socialMediaId);
    }
}
