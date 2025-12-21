package org.example.springboot4.repository;

import org.example.springboot4.model.cassandra.UserActivity;
import org.example.springboot4.model.cassandra.UserActivityKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface UserActivityRepository extends CassandraRepository<UserActivity, UserActivityKey> {
    List<UserActivity> findByKeyUserId(UUID userId);
}
