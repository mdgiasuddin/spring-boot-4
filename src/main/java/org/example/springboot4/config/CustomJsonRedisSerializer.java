package org.example.springboot4.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

public class CustomJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper mapper;
    private final JavaType javaType;

    public CustomJsonRedisSerializer(ObjectMapper mapper, JavaType javaType) {
        this.mapper = mapper;
        this.javaType = javaType;
    }

    @Override
    public byte[] serialize(T value) throws SerializationException {
        try {
            return mapper.writeValueAsBytes(value);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes.length == 0) return null;
        try {
            return mapper.readValue(bytes, javaType);
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize", e);
        }
    }
}
