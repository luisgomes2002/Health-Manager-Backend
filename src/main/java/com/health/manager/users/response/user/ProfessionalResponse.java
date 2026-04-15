package com.health.manager.users.response.user;

import com.health.manager.enums.ProfessionalType;
import com.health.manager.enums.RegistrationType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProfessionalResponse {
    private UUID id;
    private ProfessionalType type;
    private RegistrationType registrationType;
    private String registrationId;
    private String specialty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProfessionalType getType() {
        return type;
    }

    public void setType(ProfessionalType type) {
        this.type = type;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
