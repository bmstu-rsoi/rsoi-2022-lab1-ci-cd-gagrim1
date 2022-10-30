package ru.bmstu.personservice.service;

import lombok.NonNull;
import ru.bmstu.personservice.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getPersons();
    PersonDto getPersonById(@NonNull Integer id);
    Integer createPerson(@NonNull PersonDto personDto);
    PersonDto batchPerson(@NonNull Integer id,@NonNull PersonDto personRequest);
    void deletePerson(@NonNull Integer id);
}
