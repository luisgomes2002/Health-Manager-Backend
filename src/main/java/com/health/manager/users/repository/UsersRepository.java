package com.health.manager.users.repository;

import com.health.manager.users.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    @EntityGraph(attributePaths = {
            "studentProfile",
            "professionalProfile"
//            "studentsAsCoach",
//            "studentsAsNutritionist",
//            "receivedPayments"
    })
    Optional<Users> findDetailedById(UUID id);
}
