package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.cassandra.UserActivity;
import org.example.springboot4.model.dto.reqeust.ActivityRequest;
import org.example.springboot4.service.CassandraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cassandra")
@RequiredArgsConstructor
public class CassandraController {

    private final CassandraService cassandraService;

    @PostMapping("/user-activities")
    public void log(@Valid @RequestBody ActivityRequest request) {
        cassandraService.logActivity(request);
    }

    @GetMapping("/user-activities/{userId}")
    public List<UserActivity> list(@PathVariable UUID userId) {
        return cassandraService.getActivities(userId);
    }
}

