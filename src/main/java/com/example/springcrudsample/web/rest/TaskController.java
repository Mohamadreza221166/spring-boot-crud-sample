package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Task;
import com.example.springcrudsample.repository.TaskRepository;
import com.example.springcrudsample.service.TaskService;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private static final String ENTITY_NAME = "task";
    private final TaskService taskService;

    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws BadRequestAlertException {
        log.debug("ایجاد تسک جدید: {}", task);
        if (task.getId() != null) {
            throw new BadRequestAlertException("این تسک قبلا در سیستم ثبت شده است", ENTITY_NAME, Constants.ID_EXISTS);
        }
        Task result = taskService.save(task);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id", required = false) final Long id, @RequestBody Task task)
        throws BadRequestAlertException {
        log.debug("REST request to update Task : {}, {}", id, task);
        if (task.getId() == null) {
            throw new BadRequestAlertException("تسک مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, task.getId())) {
            throw new BadRequestAlertException("تسک مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!taskRepository.existsById(id)) {
            throw new BadRequestAlertException("تسک مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        Task result = taskService.update(task);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        log.debug("درخواست دریافت لیست تسک ها");
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) throws BadRequestAlertException {
        log.debug("درخواست دریافت تسک: {}", id);
        Task task = taskService.findOne(id).orElse(null);
        if (task == null)
            throw new BadRequestAlertException("تسک مورد نظر وجود ندارد", ENTITY_NAME, Constants.ID_INVALID);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        log.debug("درخواست حذف تسک: {}", id);
        taskService.delete(id);
        return ResponseEntity.ok(id);
    }
}
