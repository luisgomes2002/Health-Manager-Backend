package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Students;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PhysicalTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Students students;

    private Integer activityLevel;
    private String weeklyFrequency;
    private String currentActivities;
    private String goals;
    private String perceivedImpact;
    private String bodyComfort;
    private String exerciseRestriction;
    private String restrictedExercise;
    private String preferredShift;

    public PhysicalTraining() {
    }

    public PhysicalTraining(UUID id, Students students, Integer activityLevel, String weeklyFrequency, String currentActivities, String goals, String perceivedImpact, String bodyComfort, String exerciseRestriction, String restrictedExercise, String preferredShift) {
        this.id = id;
        this.students = students;
        this.activityLevel = activityLevel;
        this.weeklyFrequency = weeklyFrequency;
        this.currentActivities = currentActivities;
        this.goals = goals;
        this.perceivedImpact = perceivedImpact;
        this.bodyComfort = bodyComfort;
        this.exerciseRestriction = exerciseRestriction;
        this.restrictedExercise = restrictedExercise;
        this.preferredShift = preferredShift;
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

    public Integer getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Integer activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getWeeklyFrequency() {
        return weeklyFrequency;
    }

    public void setWeeklyFrequency(String weeklyFrequency) {
        this.weeklyFrequency = weeklyFrequency;
    }

    public String getCurrentActivities() {
        return currentActivities;
    }

    public void setCurrentActivities(String currentActivities) {
        this.currentActivities = currentActivities;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getPerceivedImpact() {
        return perceivedImpact;
    }

    public void setPerceivedImpact(String perceivedImpact) {
        this.perceivedImpact = perceivedImpact;
    }

    public String getBodyComfort() {
        return bodyComfort;
    }

    public void setBodyComfort(String bodyComfort) {
        this.bodyComfort = bodyComfort;
    }

    public String getExerciseRestriction() {
        return exerciseRestriction;
    }

    public void setExerciseRestriction(String exerciseRestriction) {
        this.exerciseRestriction = exerciseRestriction;
    }

    public String getRestrictedExercise() {
        return restrictedExercise;
    }

    public void setRestrictedExercise(String restrictedExercise) {
        this.restrictedExercise = restrictedExercise;
    }

    public String getPreferredShift() {
        return preferredShift;
    }

    public void setPreferredShift(String preferredShift) {
        this.preferredShift = preferredShift;
    }
}
