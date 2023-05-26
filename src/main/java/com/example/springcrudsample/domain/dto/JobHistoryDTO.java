package com.example.springcrudsample.domain.dto;

import com.example.springcrudsample.domain.entity.Department;
import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.domain.entity.Job;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

public class JobHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Job job;
    private Department department;
    private Employee employee;


    public Long getId() {
        return this.id;
    }

    public JobHistoryDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public JobHistoryDTO startDate(Instant startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public JobHistoryDTO endDate(Instant endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobHistoryDTO job(Job job) {
        this.setJob(job);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobHistoryDTO department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public JobHistoryDTO employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }
}
