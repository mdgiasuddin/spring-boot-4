package org.example.springboot4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot4.exception.ResourceNotFoundException;
import org.example.springboot4.model.dto.reqeust.PersonRequest;
import org.example.springboot4.model.dto.response.PersonResponse;
import org.example.springboot4.model.entity.Person;
import org.example.springboot4.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.springboot4.model.constant.CacheConstant.PEOPLE_CACHE;
import static org.example.springboot4.model.constant.CacheConstant.PERSON_BY_ID_CACHE;
import static org.example.springboot4.model.constant.CacheConstant.TEACHER_BY_ID_CACHE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Caching(evict = {
            @CacheEvict(cacheNames = PEOPLE_CACHE, allEntries = true)
    })
    public PersonResponse create(PersonRequest request) {
        log.info("Creating person: {}", request);
        Person person = new Person();
        person.setName(request.name());
        person.setAge(request.age());
        person.setBirthDate(request.birthDate());

        return new PersonResponse(personRepository.save(person));
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = PEOPLE_CACHE, allEntries = true)},
            put = {@CachePut(cacheNames = PERSON_BY_ID_CACHE, key = "#request.id")}
    )
    public PersonResponse update(PersonRequest request) {
        log.info("Updating person: {}", request);
        Person person = personRepository.findById(request.id())
                .orElseThrow(
                        () -> new ResourceNotFoundException("PERSON_NOT_FOUND_BY_ID", "Person not found by id: " + request.id())
                );

        person.setName(request.name());
        person.setAge(request.age());
        person.setBirthDate(request.birthDate());

        return new PersonResponse(personRepository.save(person));
    }

    @Cacheable(cacheNames = PEOPLE_CACHE, key = "'all'")
    public List<PersonResponse> getAllPeople() {
        log.info("Getting all people");
        return personRepository.findAll().stream()
                .map(PersonResponse::new)
                .toList();
    }

    @Cacheable(cacheNames = PERSON_BY_ID_CACHE, key = "#id")
    public PersonResponse getPersonById(Long id) {
        log.info("Getting person by id: {}", id);
        return personRepository.findById(id)
                .map(PersonResponse::new)
                .orElse(null);
    }

    @Cacheable(cacheNames = TEACHER_BY_ID_CACHE, key = "#id")
    public Person findPersonById(Long id) {
        log.info("Finding person by id: {}", id);
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PERSON_NOT_FOUND_BY_ID", "Person not found by id: " + id));
    }
}
