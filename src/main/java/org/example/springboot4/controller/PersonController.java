package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.PersonRequest;
import org.example.springboot4.model.dto.response.PersonResponse;
import org.example.springboot4.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public PersonResponse create(@Valid @RequestBody PersonRequest request) {
        return personService.create(request);
    }

    @PutMapping
    public PersonResponse update(@Valid @RequestBody PersonRequest request) {
        return personService.update(request);
    }

    @GetMapping
    public List<PersonResponse> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public PersonResponse getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }
}
