package javatest.ru.romanov.endpoint;

import javatest.ru.romanov.model.PersonInput;
import javatest.ru.romanov.model.PersonOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javatest.ru.romanov.model.PersonEntity;
import javatest.ru.romanov.service.PersonService;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonEndpoint {
    private final PersonService service;

    @GetMapping("/{personId}")
    public PersonOutput getById(@PathVariable("personId") Long personId) {
        return service.findById(personId);
    }

    @GetMapping
    public Page<PersonOutput> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @PostMapping
    public PersonEntity save(@RequestBody PersonInput input) {
        return service.save(input);
    }

    @PatchMapping
    public void update(@RequestParam Long id, @RequestBody PersonInput input) {
        service.update(id, input);
    }

    @DeleteMapping
    public void deleteById(Long id) {

    }

}
