package com.bikbaev.nailstudio.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "FREE_ENTRIES", indexes = {
        @Index(name = "IDX_FREE_ENTRIES_MASTER", columnList = "MASTER_ID")
})
@Entity
public class FreeEntries {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "MASTER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User master;

    @Column(name = "DATA_TIME", nullable = false)
    @NotNull
    private LocalDateTime data_time;

    @Column(name = "STATUS")
    private String status;

    public FreeEntriesStatus getStatus() {
        return status == null ? null : FreeEntriesStatus.fromId(status);
    }

    public void setStatus(FreeEntriesStatus status) {
        this.status = status == null ? null : status.getId();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}