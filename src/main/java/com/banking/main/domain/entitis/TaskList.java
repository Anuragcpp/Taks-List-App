package com.banking.main.domain.entitis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_list")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "taskList",cascade = {
            CascadeType.REMOVE,CascadeType.PERSIST
    })
    private List<Task> tasks;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id) && Objects.equals(title, taskList.title) && Objects.equals(description, taskList.description) && Objects.equals(tasks, taskList.tasks) && Objects.equals(created, taskList.created) && Objects.equals(updated, taskList.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, created, updated);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
