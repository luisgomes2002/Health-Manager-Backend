package com.health.manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class HealthRecord {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_profile_id", unique = true)
    private StudentProfile studentProfile;

    private String healthIssues;
    private String hypertension;
    private String diabetes;
    private String cardiacIssues;
    private String mentalHealth;
    private String steroidUse;

    private Boolean dailyMedication = false;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL)
    private List<Medication> medications = new ArrayList<>();

    public HealthRecord() {
    }

    public HealthRecord(UUID id, StudentProfile studentProfile, String healthIssues, String hypertension, String diabetes, String cardiacIssues, String mentalHealth, String steroidUse, Boolean dailyMedication, List<Medication> medications) {
        this.id = id;
        this.studentProfile = studentProfile;
        this.healthIssues = healthIssues;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.cardiacIssues = cardiacIssues;
        this.mentalHealth = mentalHealth;
        this.steroidUse = steroidUse;
        this.dailyMedication = dailyMedication;
        this.medications = medications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public String getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(String healthIssues) {
        this.healthIssues = healthIssues;
    }

    public String getHypertension() {
        return hypertension;
    }

    public void setHypertension(String hypertension) {
        this.hypertension = hypertension;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getCardiacIssues() {
        return cardiacIssues;
    }

    public void setCardiacIssues(String cardiacIssues) {
        this.cardiacIssues = cardiacIssues;
    }

    public String getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(String mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public String getSteroidUse() {
        return steroidUse;
    }

    public void setSteroidUse(String steroidUse) {
        this.steroidUse = steroidUse;
    }

    public Boolean getDailyMedication() {
        return dailyMedication;
    }

    public void setDailyMedication(Boolean dailyMedication) {
        this.dailyMedication = dailyMedication;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
