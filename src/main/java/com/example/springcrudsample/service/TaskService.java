package com.example.springcrudsample.service;

import com.example.springcrudsample.domain.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);

    Task update(Task task);

    List<Task> findAll();

    Optional<Task> findOne(Long id);

    void delete(Long id);
}
