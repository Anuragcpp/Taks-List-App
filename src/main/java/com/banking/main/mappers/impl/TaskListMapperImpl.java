package com.banking.main.mappers.impl;

import com.banking.main.domain.dto.TaskListDto;
import com.banking.main.domain.entitis.Task;
import com.banking.main.domain.entitis.TaskList;
import com.banking.main.domain.entitis.TaskStatus;
import com.banking.main.mappers.TaskListMapper;
import com.banking.main.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    @Autowired
    private TaskMapper taskMapper;



    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) return null;
        long completedTasks = tasks.stream().filter(
                task-> TaskStatus.COMPLETED == task.getTaskStatus()
        ).count();
        return (double) completedTasks/tasks.size();
    }
}
