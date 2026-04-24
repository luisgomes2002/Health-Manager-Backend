package com.health.manager.users.repository;

import com.health.manager.users.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Clients, UUID> {
    Optional<Clients> findByUserId(UUID userId);
}
