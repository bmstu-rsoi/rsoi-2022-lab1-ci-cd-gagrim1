package ru.bmstu.personservice.service.converter;

import ru.bmstu.personservice.dto.PersonDto;
import ru.bmstu.personservice.model.PersonEntity;
import ru.bmstu.personservice.model.PersonRequest;


public class PersonConverter {
    public static PersonDto fromPersonEntityToPersonDto(PersonEntity personEntity) {
        return new PersonDto()
                .setId(personEntity.getId())
                .setName(personEntity.getName())
                .setAddress(personEntity.getAddress())
                .setWork(personEntity.getWork())
                .setAge(personEntity.getAge());
    }

    public static PersonDto fromPersonRequestTpPersonDto(PersonRequest personRequest) {
        return new PersonDto()
                .setName(personRequest.getName())
                .setAddress(personRequest.getAddress())
                .setWork(personRequest.getWork())
                .setAge(personRequest.getAge());
    }

    public static PersonEntity fromPersonDtoToPersonEntity(PersonDto personDto) {
        return new PersonEntity()
                .setId(personDto.getId())
                .setName(personDto.getName())
                .setAddress(personDto.getAddress())
                .setWork(personDto.getWork())
                .setAge(personDto.getAge());
    }
}
