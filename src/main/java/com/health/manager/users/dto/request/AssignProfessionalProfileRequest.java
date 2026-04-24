package com.health.manager.users.dto.request;

import com.health.manager.shared.enums.ProfessionalType;
import com.health.manager.shared.enums.RegistrationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssignProfessionalProfileRequest {

    @NotNull
    private ProfessionalType type;

    @NotNull
    private RegistrationType registrationType;

    @NotBlank
    private String registrationId;

    private String specialty;

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
