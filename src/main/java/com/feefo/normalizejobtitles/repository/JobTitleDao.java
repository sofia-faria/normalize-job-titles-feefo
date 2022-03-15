package com.feefo.normalizejobtitles.repository;

import com.feefo.normalizejobtitles.model.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("base")
public interface JobTitleDao extends JpaRepository<JobTitle, UUID> {

    @Override
    List<JobTitle> findAll();

    Optional<JobTitle> findByJob(String job);
}
