package com.example.springcrudsample.service.impl;

import com.example.springcrudsample.domain.entity.Task;
import com.example.springcrudsample.repository.TaskRepository;
import com.example.springcrudsample.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        log.debug("سرویس ثبت وظیفه: {}", task);
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        log.debug("سرویس ویرایش اطلاعات تسک: {}", task);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        log.debug("سرویس لیست تسک ها");
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findOne(Long id) {
        log.debug("سرویس دریافت اطلاعات تسک: {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("سرویس حذف یک تسک: {}", id);
        taskRepository.deleteById(id);
    }

}
