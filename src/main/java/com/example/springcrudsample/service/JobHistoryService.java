package com.example.springcrudsample.service;

import com.example.springcrudsample.domain.entity.JobHistory;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface JobHistoryService {
    JobHistory save(JobHistory jobHistory);

    JobHistory update(JobHistory jobHistory);

    Page<JobHistory> findAll(Pageable pageable);

    Page<JobHistory> findAllByEmployee(Long id ,Pageable pageable) throws BadRequestAlertException;

    Optional<JobHistory> findOne(Long id);

    void delete(Long id);
}
