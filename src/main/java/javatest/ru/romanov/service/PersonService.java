package javatest.ru.romanov.service;

import javatest.ru.romanov.model.PersonInput;
import javatest.ru.romanov.model.PersonOutput;
import javatest.ru.romanov.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javatest.ru.romanov.model.PersonEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public PersonEntity save(PersonInput input) {
        if (input == null) {
            throw new RuntimeException("Переданы пустые входные данные!");
        }
        PersonEntity person = new PersonEntity();
        person.setName(input.getName());
        person.setAddress(input.getAddress());
        person.setAge(input.getAge());
        person.setJob(input.getJob());
        return repository.save(person);
    }

    public PersonOutput findById(Long id) {
        return repository.findById(id)
                .map(this::convert)
                .orElseThrow(() -> new RuntimeException("Человек с таким идентификатором не найден!"));
    }

    public Page<PersonOutput> getAll(Pageable pageable) {
        List<PersonOutput> persons = repository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(persons, pageable, persons.size());
    }

    public PersonEntity update(Long id, PersonInput input) {
        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Человек с таким идентификатором не найден!"));
        entity.setName(input.getName());
        entity.setAddress(input.getAddress());
        entity.setAge(input.getAge());
        entity.setJob(input.getJob());
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.findById(id)
                .map(this::convert)
                .ifPresentOrElse(
                        value -> repository.deleteById(id),
                        () -> {throw new RuntimeException("Человек с таким идентификатором не найден!");}
                );
    }

    private PersonOutput convert(PersonEntity entity) {
        return new PersonOutput(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getAge(),
                entity.getJob()
        );
    }
}
