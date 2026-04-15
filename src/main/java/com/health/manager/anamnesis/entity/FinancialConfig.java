package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Students;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FinancialConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Students students;

    private Integer coachDueDate;
    private Integer nutritionistDueDate;

    public FinancialConfig() {
    }

    public FinancialConfig(UUID id, Students students, Integer coachDueDate, Integer nutritionistDueDate) {
        this.id = id;
        this.students = students;
        this.coachDueDate = coachDueDate;
        this.nutritionistDueDate = nutritionistDueDate;
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
