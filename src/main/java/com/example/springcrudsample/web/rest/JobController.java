package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Job;
import com.example.springcrudsample.repository.JobRepository;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Transactional
public class JobController {

    private final Logger log = LoggerFactory.getLogger(JobController.class);

    private static final String ENTITY_NAME = "job";

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) throws BadRequestAlertException {
        log.debug("درخواست ایجاد شغل جدید: {}", job);
        if (job.getId() != null) {
            throw new BadRequestAlertException("این سمت از قبل وجود دارد.", ENTITY_NAME, Constants.ID_EXISTS);
        }
        Job result = jobRepository.save(job);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable(value = "id", required = false) final Long id, @RequestBody Job job)
        throws BadRequestAlertException {
        log.debug("درخواست بروزرسانی شغل: {}, {}", id, job);
        if (job.getId() == null) {
            throw new BadRequestAlertException("شغل مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, job.getId())) {
            throw new BadRequestAlertException("شغل مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!jobRepository.existsById(id)) {
            throw new BadRequestAlertException("شغل مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        Job result = jobRepository.save(job);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/jobs")
    public ResponseEntity<Page<Job>> getAllJobs(Pageable pageable) {
        log.debug("درخواست لیست شغل ها");
        Page<Job> page = jobRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        log.debug("درخواست دریافت اطلاعات شغل ها: {}", id);
        Job job = jobRepository.findById(id).orElse(null);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.debug("درخواست حذف شغل: {}", id);
        jobRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
