package javatest.ru.romanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javatest.ru.romanov.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
