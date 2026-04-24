package com.health.manager.users.entity;

import com.health.manager.anamnesis.entity.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Professionals coach;

    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Professionals nutritionist;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private Biometrics biometrics;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private HealthRecords healthRecords;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private Lifestyles lifestyles;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private PhysicalTraining physicalTraining;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private FinancialConfig financialConfig;

    @OneToMany(mappedBy = "clients")
    private List<Payments> payments = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialMedia> socialMedia = new ArrayList<>();

    public Clients() {
    }

    public Clients(UUID id, Users user, Professionals coach, Professionals nutritionist, LocalDateTime createdAt, LocalDateTime updatedAt, Biometrics biometrics, HealthRecords healthRecords, Lifestyles lifestyles, PhysicalTraining physicalTraining, FinancialConfig financialConfig, List<Payments> payments) {
        this.id = id;
        this.user = user;
        this.coach = coach;
        this.nutritionist = nutritionist;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.biometrics = biometrics;
        this.healthRecords = healthRecords;
        this.lifestyles = lifestyles;
        this.physicalTraining = physicalTraining;
        this.financialConfig = financialConfig;
        this.payments = payments;
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

    public Professionals getCoach() {
        return coach;
    }

    public void setCoach(Professionals coach) {
        this.coach = coach;
    }

    public Professionals getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Professionals nutritionist) {
        this.nutritionist = nutritionist;
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

    public Biometrics getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(Biometrics biometrics) {
        this.biometrics = biometrics;
    }

    public HealthRecords getHealthRecord() {
        return healthRecords;
    }

    public void setHealthRecord(HealthRecords healthRecords) {
        this.healthRecords = healthRecords;
    }

    public Lifestyles getLifestyle() {
        return lifestyles;
    }

    public void setLifestyle(Lifestyles lifestyles) {
        this.lifestyles = lifestyles;
    }

    public PhysicalTraining getPhysicalTraining() {
        return physicalTraining;
    }

    public void setPhysicalTraining(PhysicalTraining physicalTraining) {
        this.physicalTraining = physicalTraining;
    }

    public FinancialConfig getFinancialConfig() {
        return financialConfig;
    }

    public void setFinancialConfig(FinancialConfig financialConfig) {
        this.financialConfig = financialConfig;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }
}
