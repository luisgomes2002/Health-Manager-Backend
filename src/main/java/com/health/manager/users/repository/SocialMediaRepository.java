package com.health.manager.users.repository;

import com.health.manager.users.entity.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, UUID> {
}
