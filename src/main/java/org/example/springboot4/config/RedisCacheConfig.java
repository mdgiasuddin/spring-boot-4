package org.example.springboot4.config;

import org.example.springboot4.model.dto.response.PersonResponse;
import org.example.springboot4.model.entity.Person;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.List;

import static org.example.springboot4.model.constant.CacheConstant.PEOPLE_CACHE;
import static org.example.springboot4.model.constant.CacheConstant.PERSON_BY_ID_CACHE;
import static org.example.springboot4.model.constant.CacheConstant.TEACHER_BY_ID_CACHE;

@Configuration
public class RedisCacheConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(ObjectMapper objectMapper) {

        return builder -> builder
                .withCacheConfiguration(PEOPLE_CACHE,
                        listCache(objectMapper, PersonResponse.class, Duration.ofMinutes(5))
                )
                .withCacheConfiguration(PERSON_BY_ID_CACHE,
                        singleCache(objectMapper, PersonResponse.class, Duration.ofMinutes(3))
                )
                .withCacheConfiguration(TEACHER_BY_ID_CACHE,
                        singleCache(objectMapper, Person.class, Duration.ofMinutes(30))
                );
    }

    private RedisCacheConfiguration singleCache(ObjectMapper objectMapper, Class<?> type, Duration ttl) {
        JavaType javaType = objectMapper.getTypeFactory().constructType(type);

        CustomJsonRedisSerializer<?> serializer =
                new CustomJsonRedisSerializer<>(objectMapper, javaType);

        return baseConfig(ttl)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    }

    private RedisCacheConfiguration listCache(ObjectMapper objectMapper, Class<?> elementType, Duration ttl) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, elementType);

        CustomJsonRedisSerializer<?> serializer =
                new CustomJsonRedisSerializer<>(objectMapper, javaType);

        return baseConfig(ttl)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    }

    private RedisCacheConfiguration baseConfig(Duration ttl) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttl)
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string())
                );
    }

}
