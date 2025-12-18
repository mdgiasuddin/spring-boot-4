package org.example.springboot4.model.dto.reqeust;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EmployeeSearchRequest(
        String name,
        String surnameRegex,
        String country,
        String occupation,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dobFrom,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dobTo,
        String bioText,
        Double minSalary,
        Double maxSalary
) {
}
