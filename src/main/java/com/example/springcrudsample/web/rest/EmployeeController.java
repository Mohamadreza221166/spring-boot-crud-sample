package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Employee;
import com.example.springcrudsample.repository.EmployeeRepository;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import com.example.springcrudsample.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;


@RestController
@RequestMapping("/api")
@Transactional
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private static final String ENTITY_NAME = "employee";

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws BadRequestAlertException {
        log.debug("درخواست ایجاد کارمند جدید: {}", employee);
        if (employee.getId() != null) {
            throw new BadRequestAlertException("این کارمند از قبل در سیستم وجود دارد", ENTITY_NAME, Constants.ID_EXISTS);
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id", required = false) final Long id,
        @RequestBody Employee employee) throws BadRequestAlertException {
        log.debug("درخواست ویرایش کارمند: {},{}", id, employee);
        if (employee.getId() == null) {
            throw new BadRequestAlertException("اطلاعات ارسالی نامعتبر است", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, employee.getId())) {
            throw new BadRequestAlertException("اطلاعات ارسالی نامعتبر است", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!employeeRepository.existsById(id)) {
            throw new BadRequestAlertException("کارمند مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        Employee result = employeeService.update(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws BadRequestAlertException{
        log.debug("درخواست درسافت اطلاعات کارمند: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new BadRequestAlertException("کارمند مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NOT_FOUND));
        return ResponseEntity.ok(employee);
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        log.debug("درخواست حذف کاربر: {}", id);
        employeeRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
