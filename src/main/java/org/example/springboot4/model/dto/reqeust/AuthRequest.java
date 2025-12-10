package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank
        String token
) {
}
