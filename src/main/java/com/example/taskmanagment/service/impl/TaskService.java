package com.example.taskmanagment.service.impl;

import com.example.taskmanagment.exceptions.EntityNotFoundException;
import com.example.taskmanagment.model.dto.TaskRequest;
import com.example.taskmanagment.model.dto.TaskResponse;
import com.example.taskmanagment.model.dto.mapper.Mapper;
import com.example.taskmanagment.model.dto.mapper.impl.TaskMapper;
import com.example.taskmanagment.model.entity.Task;
import com.example.taskmanagment.repository.TaskRepository;
import com.example.taskmanagment.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService implements ServiceLayer<TaskRequest, TaskResponse> {

    TaskRepository taskRepository;
    TaskMapper taskMapper;

    @Override
    public TaskResponse save(TaskRequest taskRequest) {
//        Task task = taskMapper.mapToEntity(taskRequest);
//        taskRepository.save(task);
//        TaskResponse taskResponse = taskMapper.mapToResponse(task);
//        return taskResponse;

        return taskMapper.mapToResponse(taskRepository.save(taskMapper.mapToEntity(taskRequest)));
    }

    @Override
    public TaskResponse findById(Long id) {
//        Task task = taskRepository.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("Task with id" + id + " not found!!!"));
//        TaskResponse taskResponse = taskMapper.mapToResponse(task);
//        return taskResponse;

        return taskMapper.mapToResponse(byId(id));
    }

    @Override
    public List<TaskResponse> findAll() {
//        List<Task> tasks = taskRepository.findAll();
//        List<TaskResponse> taskResponses = new ArrayList<>();
//
//        for (Task task : tasks) {
//            taskResponses.add(taskMapper.mapToResponse(task));
//        }
//
//        return taskResponses;

        return taskRepository.findAll()
                .stream()
                .map(task -> taskMapper.mapToResponse(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse update(Long id, TaskRequest taskRequest) {
        Task oldTask = byId(id);
        oldTask.setTitle(taskRequest.getTitle());
        oldTask.setDescription(taskRequest.getDescription());
        oldTask.setStatus(taskRequest.getStatus());
//        taskRepository.save(oldTask);
//        return taskMapper.mapToResponse(oldTask);
        return taskMapper.mapToResponse(taskRepository.save(oldTask));

    }

    @Override
    public TaskResponse delete(Long id) {
        Task task = byId(id);
        taskRepository.deleteById(id);
        return taskMapper.mapToResponse(task);
    }

    private Task byId(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task with id:" + id + " not found!!!")
        );
    }
}
