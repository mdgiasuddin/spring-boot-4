package org.example.springboot4.model.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public record UserActivityKey(

        @PrimaryKeyColumn(
                name = "user_id",
                type = PARTITIONED
        )
        UUID userId,

        @PrimaryKeyColumn(
                name = "activity_time",
                type = CLUSTERED,
                ordering = DESCENDING
        )
        Instant activityTime

) implements Serializable {
}