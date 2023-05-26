package com.example.springcrudsample.repository;

import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.domain.entity.JobHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    Page<JobHistory> findAllByEmployee(Employee employee, Pageable pageable);

}
