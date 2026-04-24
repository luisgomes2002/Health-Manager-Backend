package com.health.manager.users.service;

import com.health.manager.users.dto.request.AssignProfessionalProfileRequest;
import com.health.manager.users.dto.response.user.ProfessionalResponse;
import com.health.manager.users.mapper.user.ProfessionalMapper;
import com.health.manager.users.repository.ProfessionalsRepository;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AssignProfessionalProfileService {

    private final UsersRepository usersRepository;
    private final ProfessionalsRepository professionalsRepository;
    private final ProfessionalMapper professionalMapper;

    public AssignProfessionalProfileService(UsersRepository usersRepository,
                                            ProfessionalsRepository professionalsRepository,
                                            ProfessionalMapper professionalMapper) {
        this.usersRepository = usersRepository;
        this.professionalsRepository = professionalsRepository;
        this.professionalMapper = professionalMapper;
    }

    @Transactional
    public ProfessionalResponse assignProfessionalProfile(UUID userId, AssignProfessionalProfileRequest request) {
        var user = usersRepository.findDetailedById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getProfessional() != null) {
            throw new RuntimeException("Usuário já possui perfil de profissional");
        }

        var professional = professionalMapper.toEntity(request);
        professional.setUser(user);
        professional.setCreatedAt(LocalDateTime.now());
        professional.setUpdatedAt(LocalDateTime.now());

        return professionalMapper.toResponse(professionalsRepository.save(professional));
    }
}
