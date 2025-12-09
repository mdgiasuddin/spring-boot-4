package org.example.springboot4.model.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CacheConstant {
    public static final String PEOPLE_CACHE = "people";
    public static final String PERSON_BY_ID_CACHE = "person_by_id";
    public static final String TEACHER_BY_ID_CACHE = "teacher_by_id";
}
