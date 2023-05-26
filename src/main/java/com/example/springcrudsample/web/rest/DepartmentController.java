package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Department;
import com.example.springcrudsample.repository.DepartmentRepository;
import com.example.springcrudsample.service.DepartmentService;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Mohammadreza Bagheri
 */
@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    private static final String ENTITY_NAME = "department";

    private final DepartmentService departmentService;

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) throws BadRequestAlertException {
        log.debug("درخواست ایجاد واحد جدید: {}", department);
        if (department.getId() != null) {
            throw new BadRequestAlertException("این واحد از قبل وجود دارد", ENTITY_NAME, Constants.ID_EXISTS);
        }
        Department result = departmentService.save(department);
        return ResponseEntity.ok(department);            
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Department department) throws BadRequestAlertException {
        log.debug("درخواست اصلاح اطلاعات واحد: {}, {}", id, department);
        if (department.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, department.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!departmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        Department result = departmentService.update(department);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        log.debug("درخواست لیست واحد ها");
        return departmentService.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        log.debug("درخواست درفافت اطلاعات واحد: {}", id);
        Department department = departmentService.findOne(id).orElse(null);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Long> deleteDepartment(@PathVariable Long id) {
        log.debug("درخواست حذف واحد: {}", id);
        departmentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
