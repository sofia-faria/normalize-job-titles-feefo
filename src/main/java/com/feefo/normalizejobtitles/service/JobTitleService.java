package com.feefo.normalizejobtitles.service;

import com.feefo.normalizejobtitles.model.JobTitle;
import com.feefo.normalizejobtitles.utils.Normalizer;
import com.feefo.normalizejobtitles.repository.JobTitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobTitleService {

    private final JobTitleDao jobTitleDao;

    @Autowired
    public JobTitleService(@Qualifier("base") JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    public List<JobTitle> getAllJobs(){
        return jobTitleDao.findAll();
    }

    public Optional<JobTitle> getJob(UUID id) {
        return jobTitleDao.findById(id);
    }

    @Transactional
    public JobTitle saveJob(JobTitle jobTitle){
        return jobTitleDao.save(jobTitle);
    }

    @Transactional
    public void deleteJob(JobTitle jobTitle){
        jobTitleDao.delete(jobTitle);
    }

    public JobTitle normalizeTitle(String title){
        Normalizer n = new Normalizer(jobTitleDao);
        return n.normalize(title);
    }
}
