package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ActivityRequest(
        @NotNull
        UUID userId,
        @NotBlank
        String type,
        @NotBlank
        String metadata
) {
}
