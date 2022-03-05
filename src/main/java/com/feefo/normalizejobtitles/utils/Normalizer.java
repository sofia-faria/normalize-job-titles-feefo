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

    //TESTAR
    public JobTitle normalize(String title){
        List<JobTitle> listNormalizedJobs = jobTitleDao.findAll();
        var q = 0.0; // quality score - if 0 not similar, if 1 equal
        JobTitle normalizedTitle = listNormalizedJobs.get(0);

        for (JobTitle normalizedJob:listNormalizedJobs) {
            var score = 0.0;
            String[] jobs = normalizedJob.getJob().split(" ");
            for(String str: jobs){
                if(title.contains(str)){
                    score += 0.5;
                }
            }
            if(q < score){
                q = score;
                normalizedTitle = normalizedJob;
            }
        }


        return normalizedTitle;
    }


}
