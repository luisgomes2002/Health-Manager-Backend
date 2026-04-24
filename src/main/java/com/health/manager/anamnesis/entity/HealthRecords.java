package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Clients;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class HealthRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Clients clients;

    private String healthIssues;
    private String hypertension;
    private String diabetes;
    private String cardiacIssues;
    private String mentalHealth;
    private String steroidUse;

    private Boolean dailyMedication = false;

    @OneToMany(mappedBy = "healthRecords", cascade = CascadeType.ALL)
    private List<Medications> medications = new ArrayList<>();

    public HealthRecords() {
    }

    public HealthRecords(UUID id, Clients clients, String healthIssues, String hypertension, String diabetes, String cardiacIssues, String mentalHealth, String steroidUse, Boolean dailyMedication, List<Medications> medications) {
        this.id = id;
        this.clients = clients;
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

    public Clients getClient() {
        return clients;
    }

    public void setClient(Clients clients) {
        this.clients = clients;
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

    public List<Medications> getMedications() {
        return medications;
    }

    public void setMedications(List<Medications> medications) {
        this.medications = medications;
    }
}
