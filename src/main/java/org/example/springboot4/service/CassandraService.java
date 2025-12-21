package org.example.springboot4.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.cassandra.UserActivity;
import org.example.springboot4.model.cassandra.UserActivityKey;
import org.example.springboot4.model.dto.reqeust.ActivityRequest;
import org.example.springboot4.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CassandraService {

    private final UserActivityRepository userActivityRepository;

    public void logActivity(ActivityRequest request) {
        userActivityRepository.save(
                new UserActivity(
                        new UserActivityKey(request.userId(), Instant.now()),
                        request.type(),
                        request.type()
                )
        );
    }

    public List<UserActivity> getActivities(UUID userId) {
        return userActivityRepository.findByKeyUserId(userId);
    }
}

