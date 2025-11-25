package com.banking.main.controller;

import com.banking.main.domain.dto.SuccessResponse;
import com.banking.main.domain.dto.TaskListDto;
import com.banking.main.domain.entitis.TaskList;
import com.banking.main.mappers.TaskListMapper;
import com.banking.main.services.TaskListService;
import com.banking.main.utils.HexWith0xToUUIDConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-list")
public class TaskListController {

    @Autowired
    private TaskListService service;

    @Autowired
    private HexWith0xToUUIDConverter hexWith0xToUUIDConverter;

    @Autowired
    private TaskListMapper taskListMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse> listTaskList(){
        List<TaskListDto> response = service.listTaskList()
                .stream()
                .map( taskListMapper::toDto)
                .toList();
        SuccessResponse successResponse = new SuccessResponse(
                HttpStatus.ACCEPTED.value(),
                "Data retrieved successfully",
                response
        );
        return new ResponseEntity<>( successResponse,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList =  service.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        SuccessResponse response = new SuccessResponse(
                HttpStatus.OK.value(),
                "Task List created Successfully",
                taskListMapper.toDto(createdTaskList)
        );
        return new  ResponseEntity<>(response,HttpStatus.OK) ;
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskById(@PathVariable(name = "task_list_id") UUID taskListId ){
        return service.getTaskList(hexWith0xToUUIDConverter.convert(String.valueOf(taskListId))).map(taskListMapper::toDto);
    }

    @PutMapping("/{task_list_id}")
    public ResponseEntity<SuccessResponse> updateTaskList(
            @PathVariable(name = "task_list_id") UUID taskListId,
            @RequestBody TaskListDto request
            ) {
        return new ResponseEntity<>( new SuccessResponse(
                HttpStatus.OK.value(),
                "Task list update with id " + taskListId,
                 taskListMapper.toDto( service.updateTaskList(taskListId, taskListMapper.fromDto( request)))
        ),HttpStatus.OK );
    }
}
