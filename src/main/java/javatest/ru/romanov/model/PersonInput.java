package javatest.ru.romanov.model;

import lombok.Value;

@Value
public class PersonInput {
    String name;
    String address;
    Short age;
    String job;
}
