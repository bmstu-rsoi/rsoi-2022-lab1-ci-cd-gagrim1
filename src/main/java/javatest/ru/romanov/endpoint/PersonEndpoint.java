package javatest.ru.romanov.endpoint;

import javatest.ru.romanov.model.PersonInput;
import javatest.ru.romanov.model.PersonOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javatest.ru.romanov.model.PersonEntity;
import javatest.ru.romanov.service.PersonService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonEndpoint {
    private final PersonService service;

    @GetMapping("/{personId}")
    public ResponseEntity<PersonOutput> getById(@PathVariable("personId") Long personId) {
        log.info("Запрос на получение личности по идентификатору " + personId +  " был сделан.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(personId));
    }

    @GetMapping
    public ResponseEntity<List<PersonOutput>> getAll() {
        log.info("Запрос на получение всех личностей был сделан.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PersonInput input) {
        log.info("Запрос на создание личности был сделан.");

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{personId}")
                .buildAndExpand(service.save(input))
                .getPath();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, location);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }

    @PatchMapping
    public ResponseEntity<PersonEntity> update(@RequestParam Long id, @RequestBody @Valid PersonInput input) {
        log.info("Запрос на обвновление личности с идентификатором " + id + " был сделан.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.update(id, input));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{personId}")
    public void deleteById(@PathVariable("personId") Long personId) {
        log.info("Запрос на удаление пользователя с идентификатором "+ personId + " был сделан.");
        service.deleteById(personId);
    }

}
