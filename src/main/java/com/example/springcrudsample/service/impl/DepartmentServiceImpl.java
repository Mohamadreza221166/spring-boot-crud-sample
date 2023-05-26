package com.example.springcrudsample.service.impl;

import com.example.springcrudsample.domain.entity.Department;
import com.example.springcrudsample.repository.DepartmentRepository;
import com.example.springcrudsample.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        log.debug("سرویس ذخیره واحد: {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        log.debug("سرویس ویرایش اطلاعات واحد: {}", department);
        return departmentRepository.save(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        log.debug("سرویس لیست واحد ها");
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findOne(Long id) {
        log.debug("سرویس دریافت اطلاعات واحد: {}", id);
        return departmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("سرویس حذف واح: {}", id);
        departmentRepository.deleteById(id);
    }
}
