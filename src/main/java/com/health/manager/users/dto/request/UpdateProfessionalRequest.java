package com.health.manager.users.dto.request;

import com.health.manager.shared.enums.ProfessionalType;
import com.health.manager.shared.enums.RegistrationType;

public class UpdateProfessionalRequest {

    private ProfessionalType type;
    private RegistrationType registrationType;
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
