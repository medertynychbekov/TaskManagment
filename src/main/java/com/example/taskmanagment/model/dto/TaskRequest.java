package com.example.taskmanagment.model.dto;

import com.example.taskmanagment.model.enums.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {

    String title;
    String description;
    Status status;
}
