package org.example.springboot4.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String code;

    public ResourceNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
