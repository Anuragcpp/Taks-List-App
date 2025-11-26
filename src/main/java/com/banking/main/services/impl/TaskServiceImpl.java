package com.banking.main.services.impl;

import com.banking.main.domain.entitis.Task;
import com.banking.main.domain.entitis.TaskList;
import com.banking.main.domain.entitis.TaskPriority;
import com.banking.main.domain.entitis.TaskStatus;
import com.banking.main.repository.TaskListRepository;
import com.banking.main.repository.TaskRepository;
import com.banking.main.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return repository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if( task.getId() != null ) throw new IllegalArgumentException("Task already has an ID!");
        if( task.getTitle() == null || task.getTitle().isBlank() ) throw new IllegalArgumentException("Title can't be empty");
        TaskPriority taskPriority = Optional.ofNullable(task.getTaskPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.PENDING;
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("Invalid Task List ID provided")
        );
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueData(),
                taskStatus,
                taskList,
                taskPriority,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return repository.save(taskToSave);
    }
}
