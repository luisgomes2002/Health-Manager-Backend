package com.health.manager.anamnesis.entity;

import com.health.manager.shared.enums.PaymentStatus;
import com.health.manager.shared.enums.ProfessionalType;
import com.health.manager.users.entity.Clients;
import com.health.manager.users.entity.Professionals;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professionals professionals;

    @Enumerated(EnumType.STRING)
    private ProfessionalType serviceType;

    private BigDecimal amount;

    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private LocalDateTime createdAt;

    public Payments() {
    }

    public Payments(UUID id, Clients clients, Professionals professionals, ProfessionalType serviceType, BigDecimal amount, LocalDateTime dueDate, LocalDateTime paymentDate, PaymentStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.clients = clients;
        this.professionals = professionals;
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

    public Clients getClient() {
        return clients;
    }

    public void setClient(Clients clients) {
        this.clients = clients;
    }

    public Professionals getProfessional() {
        return professionals;
    }

    public void setProfessional(Professionals professionals) {
        this.professionals = professionals;
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
