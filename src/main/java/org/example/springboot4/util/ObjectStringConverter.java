package org.example.springboot4.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

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
}
