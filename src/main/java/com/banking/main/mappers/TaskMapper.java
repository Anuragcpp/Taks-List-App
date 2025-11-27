package com.banking.main.mappers;

import com.banking.main.domain.dto.TaskDto;
import com.banking.main.domain.entitis.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
