package ru.bmstu.personservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.bmstu.personservice.dto.PersonDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonServiceImplTest {
    @Autowired
    private PersonService personService;

    @Test
    public void addPerson_success() {
        PersonDto personDto_test = new PersonDto()
                .setName("Kira")
                .setAddress("Moscow")
                .setWork("town")
                .setAge(48);
        int id = personService.createPerson(personDto_test);

        PersonDto personDto = personService.getPersonById(id);
        assertAll("Two objects of person",
                () -> assertEquals(personDto_test.getName(), personDto.getName()),
                () -> assertEquals(personDto_test.getAddress(), personDto.getAddress()),
                () -> assertEquals(personDto_test.getAge(), personDto.getAge()),
                () -> assertEquals(personDto_test.getWork(), personDto.getWork()));
    }

    @Test
    public void addPerson_emptyName() {
        PersonDto personDto_test = new PersonDto()
                .setAddress("Moscow")
                .setWork("town")
                .setAge(48);

        assertThrows(Exception.class,
                () -> { personService.createPerson(personDto_test); });
    }

    @Test
    public void addPerson_WrongAge() {
        PersonDto personDto_test = new PersonDto()
                .setName("Kira")
                .setAddress("Moscow")
                .setWork("town")
                .setAge(-12);

        assertThrows(Exception.class,
                () -> { personService.createPerson(personDto_test); });
    }

    @Test
    public void getAllPersons() {
        List<PersonDto> people_test = new ArrayList<>();
        PersonDto personDto_test_1 = new PersonDto()
                .setName("Kira")
                .setAddress("Moscow")
                .setWork("town")
                .setAge(48);
        PersonDto personDto_test_2 = new PersonDto()
                .setName("Nina")
                .setAddress("Sochi")
                .setWork("city")
                .setAge(22);
        people_test.add(personDto_test_1);
        people_test.add(personDto_test_2);

        personService.createPerson(personDto_test_1);
        personService.createPerson(personDto_test_2);
        List<PersonDto> people = personService.getPersons();

        assertEquals(people_test.size(), people.size());
    }

    @Test
    public void deletePerson() {
        List<PersonDto> people_test = new ArrayList<>();
        PersonDto personDto_test_1 = new PersonDto()
                .setName("Kira")
                .setAddress("Moscow")
                .setWork("town")
                .setAge(48);
        PersonDto personDto_test_2 = new PersonDto()
                .setName("Nina")
                .setAddress("Sochi")
                .setWork("city")
                .setAge(22);
        people_test.add(personDto_test_1);
        people_test.add(personDto_test_2);

        int id_1 = personService.createPerson(personDto_test_1);
        int id_2 = personService.createPerson(personDto_test_2);

        people_test.remove(personDto_test_1);
        personService.deletePerson(id_1);
        List<PersonDto> people = personService.getPersons();

        assertEquals(people_test.size(), people.size());
        assertAll("Two lists of people",
                () -> {
                    for (int i = 0; i < people_test.size(); i++) {
                        assertEquals(people_test.get(i).getName(), people.get(i).getName());
                        assertEquals(people_test.get(i).getAddress(), people.get(i).getAddress());
                        assertEquals(people_test.get(i).getAge(), people.get(i).getAge());
                        assertEquals(people_test.get(i).getWork(), people.get(i).getWork());
                    }
                });
    }

    @Test
    public void butchPerson_success() {
        PersonDto personDto_test_1 = new PersonDto()
                .setName("Kira")
                .setAddress("Moscow")
                .setWork("town")
                .setAge(48);
        PersonDto personDto_test_2 = new PersonDto()
                .setName("Kara")
                .setWork("city");
        PersonDto personDto_test_3 = new PersonDto()
                .setName("Kara")
                .setAddress("Moscow")
                .setWork("city")
                .setAge(48);
        int id = personService.createPerson(personDto_test_1);
        PersonDto personDto = personService.batchPerson(id, personDto_test_2);

        assertAll("Two objects of person",
                () -> assertEquals(personDto_test_3.getName(), personDto.getName()),
                () -> assertEquals(personDto_test_3.getAddress(), personDto.getAddress()),
                () -> assertEquals(personDto_test_3.getAge(), personDto.getAge()),
                () -> assertEquals(personDto_test_3.getWork(), personDto.getWork()));
    }
}
