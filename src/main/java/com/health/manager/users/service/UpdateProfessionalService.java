package com.health.manager.users.service;

import com.health.manager.users.dto.request.UpdateProfessionalRequest;
import com.health.manager.users.dto.response.user.ProfessionalResponse;
import com.health.manager.users.mapper.user.ProfessionalMapper;
import com.health.manager.users.repository.ProfessionalsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UpdateProfessionalService {

    private final ProfessionalsRepository professionalsRepository;
    private final ProfessionalMapper professionalMapper;

    public UpdateProfessionalService(ProfessionalsRepository professionalsRepository,
                                     ProfessionalMapper professionalMapper) {
        this.professionalsRepository = professionalsRepository;
        this.professionalMapper = professionalMapper;
    }

    @Transactional
    public ProfessionalResponse updateProfessional(UUID userId, UpdateProfessionalRequest request) {
        var professional = professionalsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil profissional não encontrado"));

        if (request.getType() != null) professional.setType(request.getType());
        if (request.getRegistrationType() != null) professional.setRegistrationType(request.getRegistrationType());
        if (request.getRegistrationId() != null) professional.setRegistrationId(request.getRegistrationId());
        if (request.getSpecialty() != null) professional.setSpecialty(request.getSpecialty());
        professional.setUpdatedAt(LocalDateTime.now());

        return professionalMapper.toResponse(professionalsRepository.save(professional));
    }
}
