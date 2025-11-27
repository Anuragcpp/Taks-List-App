package com.banking.main.mappers.impl;

import com.banking.main.domain.dto.TaskDto;
import com.banking.main.domain.entitis.Task;
import com.banking.main.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMappersImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.deuDate(),
                taskDto.status(),
                null,
                taskDto.priority(),
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueData(),
                task.getTaskPriority(),
                task.getTaskStatus()
        );
    }
}
