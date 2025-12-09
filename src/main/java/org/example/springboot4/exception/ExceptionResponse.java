package org.example.springboot4.exception;

public record ExceptionResponse(
        String code,
        String message
) {
}
