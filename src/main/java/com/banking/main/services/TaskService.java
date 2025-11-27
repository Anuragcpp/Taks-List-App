package com.banking.main.services;


import com.banking.main.domain.entitis.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Task getTask(UUID taskListId,UUID id);
    Task updateTask(UUID taskListId, UUID taskId, Task task);
    void deleteTask(UUID taskListId,UUID taskId);
}
