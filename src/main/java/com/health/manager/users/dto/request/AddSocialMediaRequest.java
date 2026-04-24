package com.health.manager.users.dto.request;

import com.health.manager.shared.enums.SocialPlatform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddSocialMediaRequest {

    @NotNull
    private SocialPlatform platform;

    @NotBlank
    private String handle;

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
}
