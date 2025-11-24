package com.banking.main.services;

import com.banking.main.domain.entitis.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList taskList);
}
