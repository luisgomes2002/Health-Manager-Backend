package com.health.manager.users.repository;

import com.health.manager.users.entity.Professionals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessionalsRepository extends JpaRepository<Professionals, UUID> {
    Optional<Professionals> findByUserId(UUID userId);
}
