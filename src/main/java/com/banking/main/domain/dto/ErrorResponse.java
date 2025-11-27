package com.banking.main.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String description
) {
}
