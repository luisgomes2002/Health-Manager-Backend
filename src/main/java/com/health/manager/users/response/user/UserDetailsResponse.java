package com.health.manager.users.response.user;

import com.health.manager.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDetailsResponse {

    private UUID id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StudenteResponse student;
    private ProfessionalResponse professional;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudenteResponse getStudent() {
        return student;
    }

    public void setStudent(StudenteResponse student) {
        this.student = student;
    }

    public ProfessionalResponse getProfessional() {
        return professional;
    }

    public void setProfessional(ProfessionalResponse professional) {
        this.professional = professional;
    }
}
