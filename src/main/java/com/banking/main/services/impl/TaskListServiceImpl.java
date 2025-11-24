package com.banking.main.services.impl;

import com.banking.main.domain.entitis.TaskList;
import com.banking.main.repository.TaskListRepository;
import com.banking.main.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
}
