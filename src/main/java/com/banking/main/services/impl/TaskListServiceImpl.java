package com.banking.main.services.impl;

import com.banking.main.TaskListAppApplication;
import com.banking.main.domain.entitis.TaskList;
import com.banking.main.repository.TaskListRepository;
import com.banking.main.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskListRepository repository;

    @Override
    public List<TaskList> listTaskList() {
        return repository.findAll();
    }


    private LocalDateTime now = LocalDateTime.now();
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null ) throw new IllegalArgumentException("Task list Already has an ID");
        if( taskList.getTitle() == null ) throw new IllegalArgumentException("Task list title must be present!");
        return repository.save( new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID id,TaskList request) {
        TaskList taskList = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task list with ID: " + id + " not found")
        );
        if(request.getTitle() == null || request.getTitle().isBlank() ) throw new IllegalArgumentException("Task list title must be present!");
        taskList.setTitle(request.getTitle());
        if(request.getDescription() != null && !request.getDescription().isBlank()) taskList.setDescription(request.getDescription());
        taskList.setUpdated(LocalDateTime.now());
        return repository.save(taskList);
    }

    @Transactional
    @Override
    public void deleteTaskList(UUID id) {
        TaskList taskList = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No task List found with id : " + id)
        );
        repository.delete(taskList);
    }
}
