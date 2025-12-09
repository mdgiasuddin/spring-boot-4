package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.constraints.NotBlank;

public record RedisPutRequest(
        @NotBlank
        String key,

        @NotBlank
        String value
) {
}
