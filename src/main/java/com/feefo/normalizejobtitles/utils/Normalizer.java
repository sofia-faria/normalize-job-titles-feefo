package com.feefo.normalizejobtitles.utils;

import com.feefo.normalizejobtitles.model.JobTitle;
import com.feefo.normalizejobtitles.repository.JobTitleDao;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class Normalizer {

    private final JobTitleDao jobTitleDao;

    public Normalizer(@Qualifier("base") JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    //should i assume that second word scores more? What about Engineer of Java?
    public String normalize(String title){
        List<JobTitle> listNormalizedJobs = jobTitleDao.findAll();
        var q = 0.0; // quality score - if 0 not similar, if 1 equal
        String normalizedTitle = title;

        for (JobTitle normalizedJob:listNormalizedJobs) {
            var score = 0.0;
            var job = normalizedJob.getJob();
            String[] jobs = job.split(" ");
            for(String str: jobs){
                if(title.toLowerCase().contains(str.toLowerCase())){
                    score += 0.5;
                }
            }
            if(q < score){
                q = score;
                normalizedTitle = job;
            }
        }


        return normalizedTitle;
    }


}
