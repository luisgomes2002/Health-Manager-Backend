package com.health.manager.users.response.user;

import java.util.UUID;

public class StudentProfileResponse {
    private UUID id;
    private UUID coachId;
    private String coachName;
    private UUID nutritionistId;
    private String nutritionistName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCoachId() {
        return coachId;
    }

    public void setCoachId(UUID coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public UUID getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(UUID nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    public String getNutritionistName() {
        return nutritionistName;
    }

    public void setNutritionistName(String nutritionistName) {
        this.nutritionistName = nutritionistName;
    }
}
