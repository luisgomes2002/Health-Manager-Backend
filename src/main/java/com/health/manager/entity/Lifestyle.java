package com.health.manager.entity;

import com.health.manager.users.entity.StudentProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class Lifestyle {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_profile_id", unique = true)
    private StudentProfile studentProfile;

    private String stressLevel;
    private String sleepHours;
    private String dietQuality;
    private String alcoholConsumption;
    private String smoking;

    public Lifestyle() {
    }

    public Lifestyle(UUID id, StudentProfile studentProfile, String stressLevel, String sleepHours, String dietQuality, String alcoholConsumption, String smoking) {
        this.id = id;
        this.studentProfile = studentProfile;
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

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
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
