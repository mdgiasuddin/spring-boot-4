package org.example.springboot4.model.cassandra;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_activity")
public record UserActivity(
        @PrimaryKey
        UserActivityKey key,

        @Column("activity_type")
        String activityType,

        @Column("metadata")
        String metadata
) {
}

