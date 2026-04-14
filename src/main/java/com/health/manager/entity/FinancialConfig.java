package com.health.manager.entity;

import com.health.manager.users.entity.StudentProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class FinancialConfig {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_profile_id", unique = true)
    private StudentProfile studentProfile;

    private Integer coachDueDate;
    private Integer nutritionistDueDate;

    public FinancialConfig() {
    }

    public FinancialConfig(UUID id, StudentProfile studentProfile, Integer coachDueDate, Integer nutritionistDueDate) {
        this.id = id;
        this.studentProfile = studentProfile;
        this.coachDueDate = coachDueDate;
        this.nutritionistDueDate = nutritionistDueDate;
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
