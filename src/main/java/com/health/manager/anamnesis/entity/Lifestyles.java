package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Students;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Lifestyles {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Students students;

    private String stressLevel;
    private String sleepHours;
    private String dietQuality;
    private String alcoholConsumption;
    private String smoking;

    public Lifestyles() {
    }

    public Lifestyles(UUID id, Students students, String stressLevel, String sleepHours, String dietQuality, String alcoholConsumption, String smoking) {
        this.id = id;
        this.students = students;
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

    public Students getStudent() {
        return students;
    }

    public void setStudent(Students students) {
        this.students = students;
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
