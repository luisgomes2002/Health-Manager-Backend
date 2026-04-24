package com.health.manager.users.dto.response.user;

import com.health.manager.shared.enums.SocialPlatform;

import java.time.LocalDateTime;
import java.util.UUID;

public class SocialMediaResponse {

    private UUID id;
    private SocialPlatform platform;
    private String handle;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SocialPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SocialPlatform platform) {
        this.platform = platform;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
