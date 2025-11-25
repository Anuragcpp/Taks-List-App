package com.banking.main.services;

import com.banking.main.domain.dto.TaskListDto;
import com.banking.main.domain.entitis.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id );
    TaskList updateTaskList(UUID id, TaskList taskList);
}
