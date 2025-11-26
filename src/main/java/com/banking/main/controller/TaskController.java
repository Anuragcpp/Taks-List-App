package com.banking.main.controller;

import com.banking.main.domain.dto.SuccessResponse;
import com.banking.main.domain.dto.TaskDto;
import com.banking.main.mappers.TaskMapper;
import com.banking.main.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task-list/{task_list_id}/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse> listTasks(@PathVariable(name = "task_list_id")UUID taskListId) {
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Task List Retrieved Successfully",
                        service.listTasks(taskListId)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createTask(
            @PathVariable(name = "task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto
            ) {
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.CREATED.value(),
                        "Task Created Successfully",
                        taskMapper.toDto(service.createTask(taskListId,taskMapper.fromDto(taskDto)))
                ),
                HttpStatus.CREATED
        );
    }
}
