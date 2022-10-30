package ru.bmstu.personservice.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.personservice.dto.PersonDto;
import ru.bmstu.personservice.model.PersonEntity;
import ru.bmstu.personservice.repository.PersonRepository;
import ru.bmstu.personservice.service.PersonService;
import ru.bmstu.personservice.service.converter.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDto> getPersons() {
        return personRepository.findAll()
                .stream()
                .map(PersonConverter::fromPersonEntityToPersonDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDto getPersonById(@NonNull Integer id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PersonConverter.fromPersonEntityToPersonDto(personEntity);
    }

    @Transactional
    public Integer createPerson(@NonNull PersonDto personDto) {
        PersonEntity personNewEntity = personRepository.save(PersonConverter.fromPersonDtoToPersonEntity(personDto));
        return PersonConverter.fromPersonEntityToPersonDto(personNewEntity).getId();
    }

    @Transactional
    public PersonDto batchPerson(@NonNull Integer id, @NonNull PersonDto personDto) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        personEntity.setName(personDto.getName());
        if (personDto.getAddress() != null) personEntity.setAddress(personDto.getAddress());
        if (personDto.getWork() != null) personEntity.setWork(personDto.getWork());
        if (personDto.getAge() != null) personEntity.setAge(personDto.getAge());

        return PersonConverter.fromPersonEntityToPersonDto(personRepository.save(personEntity));
    }

    @Transactional
    public void deletePerson(@NonNull Integer id) {
        personRepository.findById(id).ifPresent(personRepository::delete);
    }
}
