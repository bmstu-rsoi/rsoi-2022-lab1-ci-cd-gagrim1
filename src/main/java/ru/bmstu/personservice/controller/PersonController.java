package ru.bmstu.personservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bmstu.personservice.dto.PersonDto;
import ru.bmstu.personservice.model.PersonRequest;
import ru.bmstu.personservice.service.PersonService;
import ru.bmstu.personservice.service.converter.PersonConverter;

import javax.validation.Valid;
import java.util.List;

import static java.lang.Integer.parseInt;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PersonDto>> getPersons() {
        log.info(">>> Request to get all people was caught.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getPersons());
    }

    @GetMapping(value = "/{personId}", produces = "application/json")
    public ResponseEntity<?> getPersonById(@PathVariable(value = "personId") String id) {
        log.info(">>> Request to get person by id={} was caught.", id);

        Integer temp;

        try {
            temp = parseInt(id);
        }catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }

        PersonDto res = personService.getPersonById(temp);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequest personRequest) {
        log.info(">>> Request to create person was caught.");

        Integer id = personService.createPerson(PersonConverter.fromPersonRequestTpPersonDto(personRequest));

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{personId}")
                .buildAndExpand(id)
                .getPath();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, location);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable(value = "personId") String id) {
        log.info(">>> Request to delete person by id={} was caught.", id);

        Integer temp;
        try {
            temp = parseInt(id);
        }catch (NumberFormatException ignored) {
            throw new IllegalArgumentException();
        }

        personService.deletePerson(temp);
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<?> batchPerson(@PathVariable(value = "personId") String id, @Valid @RequestBody PersonRequest personRequest) {
        log.info(">>> Request to update person by id={} was caught.", id);

        Integer temp;
        try {
            temp = parseInt(id);
        }catch (NumberFormatException ignored) {
            throw new IllegalArgumentException();
        }

        PersonDto personDto = personService.batchPerson(temp, PersonConverter.fromPersonRequestTpPersonDto(personRequest));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personDto);
    }
}
