package com.banking.main.domain.dto;

public record SuccessResponse(
        int status,
        String message,
        Object data
) {
}
