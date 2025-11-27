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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Transactional
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

    @Override
    public Task getTask(UUID taskListId, UUID id) {
        return repository.findByTaskListIdAndId(taskListId,id).orElseThrow(
                () -> new IllegalArgumentException("Task Not found with id : " + id)
        );
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        /*
        if ( task.getId() == null ) {
            throw new IllegalArgumentException("Task must have an ID!");
        }
        if (!Objects.equals(taskId,task.getId())) throw new IllegalArgumentException("Task Id do not match");

         */
        if(task.getTaskStatus() == null ) throw new IllegalArgumentException("Task must have an valid Status");
        if(task.getTaskPriority() == null) throw new IllegalArgumentException("Task must have an valid Priority");
        if (task.getTitle() == null || task.getTitle().isBlank()) throw new IllegalArgumentException("Task Title can't be Empty");

        Task existingTask = repository.findByTaskListIdAndId(taskListId,taskId).orElseThrow(
                () -> new IllegalArgumentException("Task Not Found!!")
        );
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setDueData(task.getDueData());
        existingTask.setUpdated(LocalDateTime.now());

        return repository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task task = repository.findByTaskListIdAndId(taskListId,taskId).orElseThrow(
                () -> new IllegalArgumentException("Task Not found with id " + taskId)
        );
        //repository.deleteTaskListIdAndId(taskListId,taskId);
        //return null;
        repository.delete(task);
    }
}
