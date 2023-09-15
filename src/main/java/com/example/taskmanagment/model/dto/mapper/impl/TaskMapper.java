package com.example.taskmanagment.model.dto.mapper.impl;

import com.example.taskmanagment.model.dto.TaskRequest;
import com.example.taskmanagment.model.dto.TaskResponse;
import com.example.taskmanagment.model.dto.mapper.Mapper;
import com.example.taskmanagment.model.entity.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper implements Mapper<TaskRequest, Task, TaskResponse> {

    @Override
    public Task mapToEntity(TaskRequest taskRequest) {
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .created(LocalDate.now())
                .build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public TaskResponse mapToResponse(Task entity) {
        return TaskResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .created(entity.getCreated())
                .build();
    }
}
