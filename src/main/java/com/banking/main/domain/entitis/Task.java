package com.banking.main.domain.entitis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id" , updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueData;

    @Column(name = "status",nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @Column(name = "priority",nullable = false)
    private TaskPriority taskPriority;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueData=" + dueData +
                ", taskStatus=" + taskStatus +
                ", taskList=" + taskList +
                ", taskPriority=" + taskPriority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueData, task.dueData) && taskStatus == task.taskStatus && Objects.equals(taskList, task.taskList) && taskPriority == task.taskPriority && Objects.equals(created, task.created) && Objects.equals(updated, task.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueData, taskStatus, taskList, taskPriority, created, updated);
    }
}
