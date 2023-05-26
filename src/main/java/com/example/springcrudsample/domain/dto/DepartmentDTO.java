package com.example.springcrudsample.domain.dto;

import com.example.springcrudsample.domain.entity.Department;
import com.example.springcrudsample.domain.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DepartmentDTO implements Serializable {

    @Autowired
    private ModelMapper modelMapper;

    private static final long serialVersionUID = 1L;
    private Long id;
    private String departmentName;
    private Set<Employee> employees = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public DepartmentDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public DepartmentDTO departmentName(String departmentName) {
        this.setDepartmentName(departmentName);
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.setDepartment(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setDepartment(modelMapper.map(this, Department.class)));
        }
        this.employees = employees;
    }

    public DepartmentDTO employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public DepartmentDTO addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(modelMapper.map(this, Department.class));
        return this;
    }

    public DepartmentDTO removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setDepartment(null);
        return this;
    }
}
