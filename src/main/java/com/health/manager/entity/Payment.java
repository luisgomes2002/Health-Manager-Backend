package com.health.manager.entity;

import com.health.manager.enums.PaymentStatus;
import com.health.manager.enums.ProfessionalType;
import com.health.manager.users.entity.StudentProfile;
import com.health.manager.users.entity.Users;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Users professional;

    @Enumerated(EnumType.STRING)
    private ProfessionalType serviceType;

    private BigDecimal amount;

    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(UUID id, StudentProfile student, Users professional, ProfessionalType serviceType, BigDecimal amount, LocalDateTime dueDate, LocalDateTime paymentDate, PaymentStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.professional = professional;
        this.serviceType = serviceType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public Users getProfessional() {
        return professional;
    }

    public void setProfessional(Users professional) {
        this.professional = professional;
    }

    public ProfessionalType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ProfessionalType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
