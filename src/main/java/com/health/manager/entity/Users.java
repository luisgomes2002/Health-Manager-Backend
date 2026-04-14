package com.health.manager.entity;

import com.health.manager.Enum.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Users {

    @Id
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private ProfessionalProfile professionalProfile;

    @OneToMany(mappedBy = "coach")
    private List<StudentProfile> studentsAsCoach = new ArrayList<>();

    @OneToMany(mappedBy = "nutritionist")
    private List<StudentProfile> studentsAsNutritionist = new ArrayList<>();

    @OneToMany(mappedBy = "professional")
    private List<Payment> receivedPayments = new ArrayList<>();

    public Users() {
    }

    public Users(UUID id, String name, String email, Role role, String password, LocalDateTime createdAt, LocalDateTime updatedAt, StudentProfile studentProfile, ProfessionalProfile professionalProfile, List<StudentProfile> studentsAsCoach, List<StudentProfile> studentsAsNutritionist, List<Payment> receivedPayments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.studentProfile = studentProfile;
        this.professionalProfile = professionalProfile;
        this.studentsAsCoach = studentsAsCoach;
        this.studentsAsNutritionist = studentsAsNutritionist;
        this.receivedPayments = receivedPayments;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
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

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public ProfessionalProfile getProfessionalProfile() {
        return professionalProfile;
    }

    public void setProfessionalProfile(ProfessionalProfile professionalProfile) {
        this.professionalProfile = professionalProfile;
    }

    public List<StudentProfile> getStudentsAsCoach() {
        return studentsAsCoach;
    }

    public void setStudentsAsCoach(List<StudentProfile> studentsAsCoach) {
        this.studentsAsCoach = studentsAsCoach;
    }

    public List<StudentProfile> getStudentsAsNutritionist() {
        return studentsAsNutritionist;
    }

    public void setStudentsAsNutritionist(List<StudentProfile> studentsAsNutritionist) {
        this.studentsAsNutritionist = studentsAsNutritionist;
    }

    public List<Payment> getReceivedPayments() {
        return receivedPayments;
    }

    public void setReceivedPayments(List<Payment> receivedPayments) {
        this.receivedPayments = receivedPayments;
    }
}
