package ru.bmstu.personservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
public class PersonRequest {
    @NonNull
    private String name;
    private String address;
    private String work;

    @Min(0)
    private Integer age;
}
