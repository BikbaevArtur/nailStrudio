package com.bikbaev.nailstudio.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "MAKE_APPOINTMENT", indexes = {
        @Index(name = "IDX_MAKE_APPOINTMENT_CLIENT", columnList = "CLIENT_ID"),
        @Index(name = "IDX_MAKE_APPOINTMENT_MASTER", columnList = "MASTER_ID")
})
@Entity
public class MakeAppointment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @InstanceName
    private Client client;

    @JoinTable(name = "MAKE_APPOINTMENT_SERVICE_LINK",
            joinColumns = @JoinColumn(name = "MAKE_APPOINTMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Service> service;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "MASTER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User master;

    @Column(name = "DATA_TIME", nullable = false)
    @NotNull
    private LocalDateTime data_time;

    @Column(name = "PAYMENT", precision = 19, scale = 2)
    private BigDecimal payment;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    public AppointmentStatus getStatus() {
        return status == null ? null : AppointmentStatus.fromId(status);
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public LocalDateTime getData_time() {
        return data_time;
    }

    public void setData_time(LocalDateTime data_time) {
        this.data_time = data_time;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}