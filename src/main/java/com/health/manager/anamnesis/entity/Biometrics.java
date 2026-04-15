package com.health.manager.anamnesis.entity;

import com.health.manager.users.entity.Students;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometrics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Students students;

    private LocalDateTime dateOfBirth;
    private String biologicalSex;
    private String menstrualCycleImpact;

    public Biometrics() {
    }

    public Biometrics(UUID id, Students students, LocalDateTime dateOfBirth, String biologicalSex, String menstrualCycleImpact) {
        this.id = id;
        this.students = students;
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

    public Students getStudent() {
        return students;
    }

    public void setStudent(Students students) {
        this.students = students;
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
