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
        if (value == null)
            return new byte[0];

        return mapper.writeValueAsBytes(value);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0)
            return null;

        return mapper.readValue(bytes, javaType);
    }
}
