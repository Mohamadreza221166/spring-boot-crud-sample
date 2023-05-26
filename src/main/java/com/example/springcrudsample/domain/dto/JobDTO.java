package com.example.springcrudsample.domain.dto;

import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.domain.entity.Job;
import com.example.springcrudsample.domain.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class JobDTO implements Serializable {

    @Autowired
    private ModelMapper modelMapper;

    private static final long serialVersionUID = 1L;
    private Long id;
    private String jobTitle;
    private Long minSalary;
    private Long maxSalary;
    private Set<Task> tasks = new HashSet<>();
    private Employee employee;


    public Long getId() {
        return this.id;
    }

    public JobDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public JobDTO jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return this.minSalary;
    }

    public JobDTO minSalary(Long minSalary) {
        this.setMinSalary(minSalary);
        return this;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return this.maxSalary;
    }

    public JobDTO maxSalary(Long maxSalary) {
        this.setMaxSalary(maxSalary);
        return this;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public JobDTO tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public JobDTO addTask(Task task) {
        this.tasks.add(task);
        task.getJobs().add(modelMapper.map(this, Job.class));
        return this;
    }

    public JobDTO removeTask(Task task) {
        this.tasks.remove(task);
        task.getJobs().remove(this);
        return this;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public JobDTO employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }
}
