package com.example.taskmanagment.model.dto;

import com.example.taskmanagment.model.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {

    Long id;
    String title;
    String description;
    Status status;
    LocalDate created;
}
