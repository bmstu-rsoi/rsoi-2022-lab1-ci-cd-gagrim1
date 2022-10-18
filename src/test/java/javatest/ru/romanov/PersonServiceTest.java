package javatest.ru.romanov;

import javatest.ru.romanov.model.PersonInput;
import javatest.ru.romanov.model.PersonOutput;
import javatest.ru.romanov.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javatest.ru.romanov.model.PersonEntity;
import javatest.ru.romanov.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;

public class PersonServiceTest {
    @Mock
    private PersonRepository repository;

    private PersonService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new PersonService(repository);
    }


    @Test
    void shouldGetPerson() {
        PersonEntity person = new PersonEntity();
        person.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));

        Long actual = service.findById(1L).getId();
        Assertions.assertEquals(1L, actual);
    }

    @Test
    void shouldGetAllPersons() {
        List<PersonEntity> persons = new ArrayList<>();
        List<PersonOutput> personOutputs = persons
                .stream()
                .map(service::convert)
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(1, 1);
        Page<PersonOutput> expected = new PageImpl<> (personOutputs, pageable, personOutputs.size());

        Mockito.when(repository.findAll()).thenReturn(persons);

        Page<PersonOutput> actual = service.getAll(pageable);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSavePerson() {
        PersonInput input = new PersonInput(
                "name",
                "address",
                (short) 28,
                "job");
        PersonEntity expected = new PersonEntity();
        expected.setName(input.getName());
        expected.setAddress(input.getAddress());
        expected.setAge(input.getAge());
        expected.setWork(input.getWork());

        Mockito.when(repository.save(expected)).thenReturn(expected);

        service.save(input);
        Mockito.verify(repository, times(1)).save(expected);
    }

    @Test
    void shouldDeletePerson() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new PersonEntity()));
        service.deleteById(1L);
        Mockito.verify(repository, times(1)).deleteById(1L);
    }
}
