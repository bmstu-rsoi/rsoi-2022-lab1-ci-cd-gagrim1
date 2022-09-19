package javatest.ru.romanov.model;

import lombok.Value;

@Value
public class PersonOutput {
    Long id;
    String name;
    String address;
    Short age;
    String job;
}
