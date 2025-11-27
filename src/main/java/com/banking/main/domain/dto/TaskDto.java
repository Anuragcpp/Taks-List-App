package com.banking.main.domain.dto;

import com.banking.main.domain.entitis.TaskPriority;
import com.banking.main.domain.entitis.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime deuDate,
        TaskPriority priority,
        TaskStatus status
) {
}
