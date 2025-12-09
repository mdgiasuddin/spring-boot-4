package org.example.springboot4.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot4.model.entity.Person;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonResponse {
    private Long id;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
        this.birthDate = person.getBirthDate();
    }
}
