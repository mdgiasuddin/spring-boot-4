package org.example.springboot4.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.EmployeeSearchRequest;
import org.example.springboot4.model.entity.Employee;
import org.example.springboot4.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/search")
    public List<Employee> search(
            EmployeeSearchRequest request
    ) {
        return employeeService.searchPersons(request);
    }
}
