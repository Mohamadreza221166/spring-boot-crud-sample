package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.JobHistory;
import com.example.springcrudsample.repository.JobHistoryRepository;
import com.example.springcrudsample.service.JobHistoryService;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class JobHistoryController {

    private final Logger log = LoggerFactory.getLogger(JobHistoryController.class);

    private static final String ENTITY_NAME = "jobHistory";

    private final JobHistoryService jobHistoryService;

    private final JobHistoryRepository jobHistoryRepository;

    public JobHistoryController(JobHistoryService jobHistoryService, JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryService = jobHistoryService;
        this.jobHistoryRepository = jobHistoryRepository;
    }

    @PostMapping("/job-histories")
    public ResponseEntity<JobHistory> createJobHistory(@RequestBody JobHistory jobHistory) throws BadRequestAlertException {
        log.debug("درخواست ایجاد سوابق: {}", jobHistory);
        if (jobHistory.getId() != null) {
            throw new BadRequestAlertException("اطلاعات وارد شده قبلا در سیستم وجود دارد.", ENTITY_NAME, Constants.ID_EXISTS);
        }
        JobHistory result = jobHistoryService.save(jobHistory);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/job-histories/{id}")
    public ResponseEntity<JobHistory> updateJobHistory(@PathVariable(value = "id", required = false) final Long id,
        @RequestBody JobHistory jobHistory
    ) throws BadRequestAlertException {
        log.debug("درخواست بروزرسانی اطلاعات سابقه: {}, {}", id, jobHistory);
        if (jobHistory.getId() == null) {
            throw new BadRequestAlertException("سابقه مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, jobHistory.getId())) {
            throw new BadRequestAlertException("سابقه مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!jobHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("سابقه مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        JobHistory result = jobHistoryService.update(jobHistory);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/job-histories")
    public ResponseEntity<Page<JobHistory>> getAllJobHistories(Pageable pageable) {
        log.debug("درخواست دریافت لیست سوابق کارمندان");
        Page<JobHistory> page = jobHistoryService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/job-histories{id}")
    public ResponseEntity<Page<JobHistory>> getAllJobHistoriesByEmploy(@PathVariable Long id, Pageable pageable) throws BadRequestAlertException {
        log.debug("درخواست دریافت لیست سوابق کارمند");
        Page<JobHistory> page = jobHistoryService.findAllByEmployee(id, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/job-histories/{id}")
    public ResponseEntity<JobHistory> getJobHistory(@PathVariable Long id) {
        log.debug("درخواست دریافت سوابق: {}", id);
        JobHistory jobHistory = jobHistoryRepository.findById(id).orElse(null);
        return ResponseEntity.ok(jobHistory);
    }

    @DeleteMapping("/job-histories/{id}")
    public ResponseEntity<Void> deleteJobHistory(@PathVariable Long id) {
        log.debug("REST request to delete JobHistory : {}", id);
        jobHistoryService.delete(id);
        return ResponseEntity.ok(null);
    }
}
