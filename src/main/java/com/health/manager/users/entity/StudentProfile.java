package com.health.manager.users.entity;

import com.health.manager.entity.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class StudentProfile {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Users coach;

    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Users nutritionist;

    @OneToOne(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private Biometrics biometrics;

    @OneToOne(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private HealthRecord healthRecord;

    @OneToOne(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private Lifestyle lifestyle;

    @OneToOne(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private PhysicalTraining physicalTraining;

    @OneToOne(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private FinancialConfig finance;

    @OneToMany(mappedBy = "student")
    private List<Payment> payments = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentProfile() {
    }

    public StudentProfile(UUID id, Users users, Users coach, Users nutritionist, Biometrics biometrics, HealthRecord healthRecord, Lifestyle lifestyle, PhysicalTraining physicalTraining, FinancialConfig finance, List<Payment> payments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.users = users;
        this.coach = coach;
        this.nutritionist = nutritionist;
        this.biometrics = biometrics;
        this.healthRecord = healthRecord;
        this.lifestyle = lifestyle;
        this.physicalTraining = physicalTraining;
        this.finance = finance;
        this.payments = payments;
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

    public Users getCoach() {
        return coach;
    }

    public void setCoach(Users coach) {
        this.coach = coach;
    }

    public Users getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Users nutritionist) {
        this.nutritionist = nutritionist;
    }

    public Biometrics getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(Biometrics biometrics) {
        this.biometrics = biometrics;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public PhysicalTraining getPhysicalTraining() {
        return physicalTraining;
    }

    public void setPhysicalTraining(PhysicalTraining physicalTraining) {
        this.physicalTraining = physicalTraining;
    }

    public FinancialConfig getFinance() {
        return finance;
    }

    public void setFinance(FinancialConfig finance) {
        this.finance = finance;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
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
