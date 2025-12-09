package org.example.springboot4.model.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonRequest(

        Long id,

        @NotBlank
        String name,

        @NotNull
        Integer age,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {
}
