package com.example.springcrudsample.domain.dto;

import com.example.springcrudsample.domain.entity.Department;
import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.domain.entity.Job;
import com.example.springcrudsample.domain.entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


public class EmployeeDTO implements Serializable {

    @Autowired
    private ModelMapper modelMapper;

    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Instant hireDate;
    private Long salary;
    private Long commissionPct;
    private Location location;
    private Set<Job> jobs = new HashSet<>();
    private EmployeeDTO manager;
    private Department department;

    public Long getId() {
        return this.id;
    }

    public EmployeeDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public EmployeeDTO firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public EmployeeDTO lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public EmployeeDTO email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public EmployeeDTO phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return this.hireDate;
    }

    public EmployeeDTO hireDate(Instant hireDate) {
        this.setHireDate(hireDate);
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSalary() {
        return this.salary;
    }

    public EmployeeDTO salary(Long salary) {
        this.setSalary(salary);
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCommissionPct() {
        return this.commissionPct;
    }

    public EmployeeDTO commissionPct(Long commissionPct) {
        this.setCommissionPct(commissionPct);
        return this;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EmployeeDTO location(Location location) {
        this.setLocation(location);
        return this;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        if (this.jobs != null) {
            this.jobs.forEach(i -> i.setEmployee(null));
        }
        if (jobs != null) {
            jobs.forEach(i -> i.setEmployee(modelMapper.map(this, Employee.class)));
        }
        this.jobs = jobs;
    }

    public EmployeeDTO jobs(Set<Job> jobs) {
        this.setJobs(jobs);
        return this;
    }

    public EmployeeDTO addJob(Job job) {
        this.jobs.add(job);
        job.setEmployee(modelMapper.map(this, Employee.class));
        return this;
    }

    public EmployeeDTO removeJob(Job job) {
        this.jobs.remove(job);
        job.setEmployee(null);
        return this;
    }

    public EmployeeDTO getManager() {
        return this.manager;
    }

    public void setManager(EmployeeDTO employee) {
        this.manager = employee;
    }

    public EmployeeDTO manager(EmployeeDTO employee) {
        this.setManager(employee);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeDTO department(Department department) {
        this.setDepartment(department);
        return this;
    }

}
