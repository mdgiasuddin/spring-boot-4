package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record RedisObjectPutRequest(
        @NotBlank
        String key,

        @Valid
        RedisPutObject value
) {

}
