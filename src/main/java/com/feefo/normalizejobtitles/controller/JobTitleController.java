package com.feefo.normalizejobtitles.controller;

import com.feefo.normalizejobtitles.dto.JobTitleDto;
import com.feefo.normalizejobtitles.model.JobTitle;
import com.feefo.normalizejobtitles.service.JobTitleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/jobtitle")
public class JobTitleController {


    private final JobTitleService jobTitleService;

    @Autowired
    public JobTitleController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }


    @GetMapping
    public ResponseEntity<List<JobTitle>> getAllJobs(){
        return ResponseEntity.status(HttpStatus.OK).body(jobTitleService.getAllJobs());
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getJob(@PathVariable(value = "id") UUID id){
        Optional<JobTitle> jobOpt = jobTitleService.getJob(id);
        if(jobOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(jobOpt.get());
    }

    @PostMapping
    public ResponseEntity<Object> addJob(@RequestBody @Valid JobTitleDto jobTitleDto){
        var job = new JobTitle();
        BeanUtils.copyProperties(jobTitleDto, job);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTitleService.saveJob(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable(value = "id") UUID id){
        Optional<JobTitle> jobOpt = jobTitleService.getJob(id);
        if(jobOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }

        jobTitleService.deleteJob(jobOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid JobTitleDto jobTitleDto){
        Optional<JobTitle> jobOpt = jobTitleService.getJob(id);
        if(jobOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }

        var job = jobOpt.get();
        job.setJob(jobTitleDto.getJob());

        /*Another way of updating the object*/
        /*var job1 = new JobTitle();
        BeanUtils.copyProperties(jobTitleDto, job1);
        job1.setId(jobOpt.get().getId());*/

        return ResponseEntity.status(HttpStatus.OK).body(jobTitleService.saveJob(job));
    }

    @PostMapping("/normalize")
    public ResponseEntity<Object> normalizeTitle(@RequestBody @Valid String title){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTitleService.normalizeTitle(title));
    }


}
