package com.health.manager.users.mapper.user;

import com.health.manager.users.dto.request.AssignProfessionalProfileRequest;
import com.health.manager.users.dto.response.user.ProfessionalResponse;
import com.health.manager.users.entity.Professionals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionalMapper {
    Professionals toEntity(AssignProfessionalProfileRequest request);
    ProfessionalResponse toResponse(Professionals professionals);
}
