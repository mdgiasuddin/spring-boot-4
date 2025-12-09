package org.example.springboot4.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private LocalDate birthDate;
}
