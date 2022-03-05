package com.feefo.normalizejobtitles.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(nullable = false, name = "job")
    private String job;

    public JobTitle() {
    }

    public JobTitle(UUID id, String job) {
        this.id = id;
        this.job = job;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
