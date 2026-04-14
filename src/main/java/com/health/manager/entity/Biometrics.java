package com.health.manager.entity;

import com.health.manager.users.entity.StudentProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometrics {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_profile_id", unique = true)
    private StudentProfile studentProfile;

    private LocalDateTime dateOfBirth;

    private String biologicalSex;

    private String menstrualCycleImpact;

    public Biometrics() {
    }

    public Biometrics(UUID id, StudentProfile studentProfile, LocalDateTime dateOfBirth, String biologicalSex, String menstrualCycleImpact) {
        this.id = id;
        this.studentProfile = studentProfile;
        this.dateOfBirth = dateOfBirth;
        this.biologicalSex = biologicalSex;
        this.menstrualCycleImpact = menstrualCycleImpact;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(String biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    public String getMenstrualCycleImpact() {
        return menstrualCycleImpact;
    }

    public void setMenstrualCycleImpact(String menstrualCycleImpact) {
        this.menstrualCycleImpact = menstrualCycleImpact;
    }
}
