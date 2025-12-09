package org.example.springboot4.model.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisPutObject {
    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
