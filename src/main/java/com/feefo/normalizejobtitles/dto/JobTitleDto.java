package com.feefo.normalizejobtitles.dto;

import javax.validation.constraints.NotNull;

public class JobTitleDto {

    @NotNull
    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
