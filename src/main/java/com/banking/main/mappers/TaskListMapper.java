package com.banking.main.mappers;

import com.banking.main.domain.dto.TaskListDto;
import com.banking.main.domain.entitis.TaskList;

import java.util.List;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
