package com.health.manager.anamnesis.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Medications {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "health_record_id")
    private HealthRecords healthRecords;

    private String name;
    private String pathology;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Medications() {
    }

    public Medications(UUID id, HealthRecords healthRecords, String name, String pathology, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.healthRecords = healthRecords;
        this.name = name;
        this.pathology = pathology;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HealthRecords getHealthRecord() {
        return healthRecords;
    }

    public void setHealthRecord(HealthRecords healthRecords) {
        this.healthRecords = healthRecords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
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
