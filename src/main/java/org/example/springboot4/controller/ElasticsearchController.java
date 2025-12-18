package org.example.springboot4.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.EmployeeSearchRequest;
import org.example.springboot4.model.entity.Employee;
import org.example.springboot4.service.ElasticSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/elasticsearch")
@RequiredArgsConstructor
public class ElasticsearchController {

    private final ElasticSearchService elasticSearchService;

    @GetMapping("/employees/search")
    public List<Employee> search(
            EmployeeSearchRequest request
    ) {
        return elasticSearchService.searchPersons(request);
    }
}
