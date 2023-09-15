package com.example.taskmanagment.controller;

import com.example.taskmanagment.model.dto.TaskRequest;
import com.example.taskmanagment.model.dto.TaskResponse;
import com.example.taskmanagment.service.impl.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Task controller", description = "These endpoints for Task CRUD")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @PostMapping
    @Operation(description = "This endpoint help for saving the Task in DB")
    public TaskResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(description = "This endpoint help for getting Task object by ID")
    public TaskResponse findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(description = "This endpoint help for getting all Task objects")
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @PutMapping("{id}")
    @Operation(description = "This endpoint help for changing the Task object by ID")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "This endpoint help for delete object by ID")
    public TaskResponse deleteById(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
