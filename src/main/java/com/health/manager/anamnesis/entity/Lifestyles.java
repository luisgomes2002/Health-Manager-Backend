package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Clients;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Lifestyles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Clients clients;

    private String stressLevel;
    private String sleepHours;
    private String dietQuality;
    private String alcoholConsumption;
    private String smoking;

    public Lifestyles() {
    }

    public Lifestyles(UUID id, Clients clients, String stressLevel, String sleepHours, String dietQuality, String alcoholConsumption, String smoking) {
        this.id = id;
        this.clients = clients;
        this.stressLevel = stressLevel;
        this.sleepHours = sleepHours;
        this.dietQuality = dietQuality;
        this.alcoholConsumption = alcoholConsumption;
        this.smoking = smoking;
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

    public String getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(String stressLevel) {
        this.stressLevel = stressLevel;
    }

    public String getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(String sleepHours) {
        this.sleepHours = sleepHours;
    }

    public String getDietQuality() {
        return dietQuality;
    }

    public void setDietQuality(String dietQuality) {
        this.dietQuality = dietQuality;
    }

    public String getAlcoholConsumption() {
        return alcoholConsumption;
    }

    public void setAlcoholConsumption(String alcoholConsumption) {
        this.alcoholConsumption = alcoholConsumption;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }
}
