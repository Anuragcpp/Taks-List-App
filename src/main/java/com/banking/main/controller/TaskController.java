package com.banking.main.controller;

import com.banking.main.domain.dto.SuccessResponse;
import com.banking.main.domain.dto.TaskDto;
import com.banking.main.mappers.TaskMapper;
import com.banking.main.services.TaskService;
import com.banking.main.utils.HexWith0xToUUIDConverter;
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

    @Autowired
    private HexWith0xToUUIDConverter hexWith0xToUUIDConverter;

    @GetMapping
    public ResponseEntity<SuccessResponse> listTasks(@PathVariable(name = "task_list_id")UUID taskListId) {
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Task List Retrieved Successfully",
                        service.listTasks(taskListId).stream().map(taskMapper::toDto).toList()
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

    @GetMapping("/{task_id}")
    public ResponseEntity<SuccessResponse> getTask(
            @PathVariable (name = "task_list_id") UUID taskListId,
            @PathVariable ( name = "task_id") UUID taskId
    ){
        UUID binaryTaskListId = hexWith0xToUUIDConverter.convert(String.valueOf(taskListId));
        UUID binaryTaskId = hexWith0xToUUIDConverter.convert(String.valueOf(taskId));
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Task Retrived Successfully",
                        taskMapper.toDto( service.getTask(taskListId,taskId ) )
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<SuccessResponse> updateTask(
            @PathVariable(name = "task_list_id") UUID taskListId,
            @PathVariable(name = "task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ){
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Task Updated Successfully",
                        taskMapper.toDto(
                                service.updateTask(
                                        taskListId,
                                        taskId,
                                        taskMapper.fromDto(
                                                taskDto
                                        )
                                )
                        )
                ),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{task_id}")
    public ResponseEntity<SuccessResponse> deleteTask(
            @PathVariable(name = "task_list_id") UUID taskListId,
            @PathVariable(name = "task_id") UUID taskId
    ){
        service.deleteTask(taskListId,taskId);
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Task Deleted Successfully",
                       null
                ),
                HttpStatus.OK
        );
    }

}
