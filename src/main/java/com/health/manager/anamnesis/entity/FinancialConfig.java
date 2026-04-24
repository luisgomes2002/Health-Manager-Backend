package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Clients;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FinancialConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Clients clients;

    private Integer coachDueDate;
    private Integer nutritionistDueDate;

    public FinancialConfig() {
    }

    public FinancialConfig(UUID id, Clients clients, Integer coachDueDate, Integer nutritionistDueDate) {
        this.id = id;
        this.clients = clients;
        this.coachDueDate = coachDueDate;
        this.nutritionistDueDate = nutritionistDueDate;
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

    public Integer getCoachDueDate() {
        return coachDueDate;
    }

    public void setCoachDueDate(Integer coachDueDate) {
        this.coachDueDate = coachDueDate;
    }

    public Integer getNutritionistDueDate() {
        return nutritionistDueDate;
    }

    public void setNutritionistDueDate(Integer nutritionistDueDate) {
        this.nutritionistDueDate = nutritionistDueDate;
    }
}
