package javatest.ru.romanov.model;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class PersonInput {
    @NotNull
    String name;
    @NotNull
    String address;
    @NotNull
    @Min(0)
    @Max(120)
    Short age;
    @NotNull
    String work;
}
