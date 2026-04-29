package org.example.springboot4.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ObjectStringConverter {

    private final ObjectMapper objectMapper;

    public String convertToString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T convertToObject(String string, Class<T> clazz) {
        return objectMapper.readValue(string, clazz);
    }

    public <T> List<T> convertToList(String string, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);

        return objectMapper.readValue(string, javaType);
    }

    public <K, V> Map<K, V> convertToMap(String string, Class<K> keyClass, Class<V> valueClass) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructMapType(HashMap.class, keyClass, valueClass);

        return objectMapper.readValue(string, javaType);
    }
}
