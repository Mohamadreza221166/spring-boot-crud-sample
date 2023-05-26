package com.example.springcrudsample.service;

import com.example.springcrudsample.domain.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department save(Department department);

    Department update(Department department);

    List<Department> findAll();

    Optional<Department> findOne(Long id);

    void delete(Long id);
}
