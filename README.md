### Introduction

This project uses Spring-boot latest version (Spring-boot:4.x.x).

### Project Setup

##### Setup Postgres

`sudo docker compose -f docker-compose-postgres.yml up -d`

##### Create a database named `spring_test`

##### Setup Elasticsearch

`sudo docker compose -f docker-compose-elasticsearch.yml up -d`

##### Setup Kafka Cluster

`sudo docker compose -f docker-compose-kafka.yml up -d`

#### Setup Redis Cluster

`sudo docker compose -f docker-compose-redis.yml up -d`

#### Setup Cassandra

`sudo docker compose -f docker-compose-cassandra.yml up -d`

##### Open Cassandra Console

`docker exec -it cassandra cqlsh`

##### Create Keyspace

```
CREATE KEYSPACE userks
WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};
```

##### Create Table

```
USE userks;

CREATE TABLE user_activity (
  user_id UUID,
  activity_time TIMESTAMP,
  activity_type TEXT,
  metadata TEXT,
  PRIMARY KEY (user_id, activity_time)
) WITH CLUSTERING ORDER BY (activity_time DESC);
```

