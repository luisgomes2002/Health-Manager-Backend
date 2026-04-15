package com.health.manager.users.entity;

import com.health.manager.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Students students;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Professionals professionals;

    public Users() {
    }

    public Users(UUID id, String name, String email, Role role, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Students students, Professionals professionals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.students = students;
        this.professionals = professionals;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Students getStudent() {
        return students;
    }

    public void setStudent(Students students) {
        this.students = students;
    }

    public Professionals getProfessional() {
        return professionals;
    }

    public void setProfessional(Professionals professionals) {
        this.professionals = professionals;
    }
}
