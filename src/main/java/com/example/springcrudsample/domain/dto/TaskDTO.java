package com.example.springcrudsample.domain.dto;

import com.example.springcrudsample.domain.entity.Job;
import com.example.springcrudsample.domain.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TaskDTO implements Serializable {

    @Autowired
    private ModelMapper modelMapper;

    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String description;
    private Set<Job> jobs = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public TaskDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public TaskDTO title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskDTO description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        if (this.jobs != null) {
            this.jobs.forEach(i -> i.removeTask(modelMapper.map(this, Task.class)));
        }
        if (jobs != null) {
            jobs.forEach(i -> i.addTask(modelMapper.map(this, Task.class)));
        }
        this.jobs = jobs;
    }

    public TaskDTO jobs(Set<Job> jobs) {
        this.setJobs(jobs);
        return this;
    }

    public TaskDTO addJob(Job job) {
        this.jobs.add(job);
        job.getTasks().add(modelMapper.map(this, Task.class));
        return this;
    }

    public TaskDTO removeJob(Job job) {
        this.jobs.remove(job);
        job.getTasks().remove(this);
        return this;
    }
}
