package com.health.manager.users.entity;

import com.health.manager.enums.RegistrationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ProfessionalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users users;

    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    private String registrationId;

    private String specialty;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProfessionalProfile() {
    }

    public ProfessionalProfile(UUID id, Users users, RegistrationType registrationType, String registrationId, String specialty, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.users = users;
        this.registrationType = registrationType;
        this.registrationId = registrationId;
        this.specialty = specialty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
