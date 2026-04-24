package com.health.manager.users.dto.request;

import java.util.UUID;

public class AssignClientProfileRequest {

    private UUID coachId;
    private UUID nutritionistId;

    public UUID getCoachId() {
        return coachId;
    }

    public void setCoachId(UUID coachId) {
        this.coachId = coachId;
    }

    public UUID getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(UUID nutritionistId) {
        this.nutritionistId = nutritionistId;
    }
}
