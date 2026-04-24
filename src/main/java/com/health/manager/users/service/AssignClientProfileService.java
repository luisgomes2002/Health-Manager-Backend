package com.health.manager.users.service;

import com.health.manager.users.dto.request.AssignClientProfileRequest;
import com.health.manager.users.dto.response.user.UserDetailsResponse;
import com.health.manager.users.entity.Clients;
import com.health.manager.users.mapper.user.UserDetailsMapper;
import com.health.manager.users.repository.ClientsRepository;
import com.health.manager.users.repository.ProfessionalsRepository;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AssignClientProfileService {

    private final UsersRepository usersRepository;
    private final ClientsRepository clientsRepository;
    private final ProfessionalsRepository professionalsRepository;
    private final UserDetailsMapper userDetailsMapper;

    public AssignClientProfileService(UsersRepository usersRepository,
                                      ClientsRepository clientsRepository,
                                      ProfessionalsRepository professionalsRepository,
                                      UserDetailsMapper userDetailsMapper) {
        this.usersRepository = usersRepository;
        this.clientsRepository = clientsRepository;
        this.professionalsRepository = professionalsRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Transactional
    public UserDetailsResponse assignClientProfile(UUID userId, AssignClientProfileRequest request) {
        var user = usersRepository.findDetailedById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getClient() != null) {
            throw new RuntimeException("Usuário já possui perfil de cliente");
        }

        var client = new Clients();
        client.setUser(user);
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());

        if (request.getCoachId() != null) {
            var coach = professionalsRepository.findById(request.getCoachId())
                    .orElseThrow(() -> new RuntimeException("Coach não encontrado"));
            client.setCoach(coach);
        }

        if (request.getNutritionistId() != null) {
            var nutritionist = professionalsRepository.findById(request.getNutritionistId())
                    .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado"));
            client.setNutritionist(nutritionist);
        }

        clientsRepository.save(client);

        return userDetailsMapper.toResponse(
                usersRepository.findDetailedById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
        );
    }
}
