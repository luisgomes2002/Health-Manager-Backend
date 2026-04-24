package com.health.manager.users.service;

import com.health.manager.shared.enums.ProfessionalType;
import com.health.manager.shared.enums.Role;
import com.health.manager.users.dto.request.RegisterClientRequest;
import com.health.manager.users.dto.response.user.RegisterClientResponse;
import com.health.manager.users.entity.Clients;
import com.health.manager.users.entity.Users;
import com.health.manager.users.repository.ClientsRepository;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegisterClientService {

    private final UsersRepository usersRepository;
    private final ClientsRepository clientsRepository;

    public RegisterClientService(UsersRepository usersRepository, ClientsRepository clientsRepository) {
        this.usersRepository = usersRepository;
        this.clientsRepository = clientsRepository;
    }

    @Transactional
    public RegisterClientResponse registerClient(UUID professionalUserId, RegisterClientRequest request) {
        var professionalUser = usersRepository.findDetailedById(professionalUserId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        var professional = professionalUser.getProfessional();
        if (professional == null) {
            throw new RuntimeException("Usuário não possui perfil de profissional");
        }

        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        String generatedPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        var clientUser = new Users();
        clientUser.setName(request.getName());
        clientUser.setEmail(request.getEmail());
        clientUser.setRole(Role.CLIENT);
        clientUser.setPassword(generatedPassword);
        var savedUser = usersRepository.save(clientUser);

        var client = new Clients();
        client.setUser(savedUser);
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());

        if (professional.getType() == ProfessionalType.COACH) {
            client.setCoach(professional);
        } else if (professional.getType() == ProfessionalType.NUTRITIONIST) {
            client.setNutritionist(professional);
        }

        clientsRepository.save(client);

        // TODO: send email to savedUser.getEmail() with credentials (login + generatedPassword)

        var response = new RegisterClientResponse();
        response.setUserId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setTemporaryPassword(generatedPassword);
        return response;
    }
}
