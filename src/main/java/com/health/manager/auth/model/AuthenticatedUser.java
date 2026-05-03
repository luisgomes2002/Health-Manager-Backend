package com.health.manager.auth.model;

import java.util.UUID;

public record AuthenticatedUser(UUID userId, String name, String email, String role) {
}
