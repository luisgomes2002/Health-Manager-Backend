package com.health.manager.users.dto.response.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ClientResponse {

    private UUID id;

    private UUID coachId;
    private String coachName;

    private UUID nutritionistId;
    private String nutritionistName;

    private List<SocialMediaResponse> socialMedia;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public List<SocialMediaResponse> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMediaResponse> socialMedia) {
        this.socialMedia = socialMedia;
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
