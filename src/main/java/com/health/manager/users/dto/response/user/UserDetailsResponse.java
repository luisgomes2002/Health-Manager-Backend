package com.health.manager.users.dto.response.user;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
