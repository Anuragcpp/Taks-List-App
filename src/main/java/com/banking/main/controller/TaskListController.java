package com.banking.main.controller;

import com.banking.main.domain.dto.TaskListDto;
import com.banking.main.domain.entitis.TaskList;
import com.banking.main.mappers.TaskListMapper;
import com.banking.main.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-list")
public class TaskListController {

    @Autowired
    private TaskListService service;

    @Autowired
    private TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskList(){
        return service.listTaskList()
                .stream()
                .map( taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList =  service.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }
}
