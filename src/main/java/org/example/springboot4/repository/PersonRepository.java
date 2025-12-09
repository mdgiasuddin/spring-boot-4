package org.example.springboot4.repository;

import org.example.springboot4.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
