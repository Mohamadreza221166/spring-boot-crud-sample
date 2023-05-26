package com.example.springcrudsample.service.impl;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import com.example.springcrudsample.repository.EmployeeRepository;
import com.example.springcrudsample.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        log.debug("سرویس ثبت کارمند: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        log.debug("سرویس ویرایش اطلاعات کارمند: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        log.debug("سرویس لیستکارمندان");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findOne(Long id) {
        log.debug("سرویس دریافت اطلاعات کارمند: {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("سرویس حذف یک کارمند: {}", id);
        employeeRepository.deleteById(id);
    }

}
