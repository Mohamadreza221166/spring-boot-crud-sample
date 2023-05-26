package com.example.springcrudsample.service.impl;

import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.domain.entity.JobHistory;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import com.example.springcrudsample.repository.EmployeeRepository;
import com.example.springcrudsample.repository.JobHistoryRepository;
import com.example.springcrudsample.service.JobHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

    private final Logger log = LoggerFactory.getLogger(JobHistoryServiceImpl.class);

    private final JobHistoryRepository jobHistoryRepository;

    private final EmployeeRepository employeeRepository;

    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository, EmployeeRepository employeeRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public JobHistory save(JobHistory jobHistory) {
        log.debug("سرویس ایجاد سوابق: {}", jobHistory);
        return jobHistoryRepository.save(jobHistory);
    }

    @Override
    public JobHistory update(JobHistory jobHistory) {
        log.debug("سرویس اصلاح اطلاعات سوابق: {}", jobHistory);
        return jobHistoryRepository.save(jobHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobHistory> findAll(Pageable pageable) {
        log.debug("سرویس دریافت اطلاعات سوابق: {}");
        return jobHistoryRepository.findAll(pageable);
    }

    @Override
    public Page<JobHistory> findAllByEmployee(Long id, Pageable pageable) throws BadRequestAlertException {
        log.debug("سرویس دریافت لیست سوابق کارمند: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new BadRequestAlertException("کارمند موردنظر یافت نشد.") );
        return jobHistoryRepository.findAllByEmployee(employee, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobHistory> findOne(Long id) {
        log.debug("سرویس دریافت اطلاعات سوابق: {}", id);
        return jobHistoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("سرویس حذف سوابق: {}", id);
        jobHistoryRepository.deleteById(id);
    }
}
