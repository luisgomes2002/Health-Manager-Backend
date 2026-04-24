package com.health.manager.users.entity;

import com.health.manager.anamnesis.entity.Payments;
import com.health.manager.shared.enums.ProfessionalType;
import com.health.manager.shared.enums.RegistrationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Professionals {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

    @Enumerated(EnumType.STRING)
    private ProfessionalType type;

    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    private String registrationId;

    private String specialty;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "coach")
    private List<Clients> coachedClients = new ArrayList<>();

    @OneToMany(mappedBy = "nutritionist")
    private List<Clients> nutritionClients = new ArrayList<>();

    @OneToMany(mappedBy = "professionals")
    private List<Payments> receivedPayments = new ArrayList<>();

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialMedia> socialMedia = new ArrayList<>();

    public Professionals() {
    }

    public Professionals(UUID id, Users user, ProfessionalType type, RegistrationType registrationType, String registrationId, String specialty, LocalDateTime createdAt, LocalDateTime updatedAt, List<Clients> coachedClients, List<Clients> nutritionClients, List<Payments> receivedPayments) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.registrationType = registrationType;
        this.registrationId = registrationId;
        this.specialty = specialty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.coachedClients = coachedClients;
        this.nutritionClients = nutritionClients;
        this.receivedPayments = receivedPayments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public List<Clients> getCoachedClients() {
        return coachedClients;
    }

    public void setCoachedClients(List<Clients> coachedClients) {
        this.coachedClients = coachedClients;
    }

    public List<Clients> getNutritionClients() {
        return nutritionClients;
    }

    public void setNutritionClients(List<Clients> nutritionClients) {
        this.nutritionClients = nutritionClients;
    }

    public List<Payments> getReceivedPayments() {
        return receivedPayments;
    }

    public void setReceivedPayments(List<Payments> receivedPayments) {
        this.receivedPayments = receivedPayments;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }
}
