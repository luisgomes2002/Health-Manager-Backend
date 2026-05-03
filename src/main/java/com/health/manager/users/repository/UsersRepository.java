package com.health.manager.users.repository;

import com.health.manager.users.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    Page<Users> findAllByActiveTrue(Pageable pageable);

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    @EntityGraph(attributePaths = {
            "clients",
            "professionals"
    })
    Optional<Users> findDetailedById(UUID id);
}
