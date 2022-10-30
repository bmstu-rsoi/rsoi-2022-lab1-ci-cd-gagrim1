package ru.bmstu.personservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
@Validated
@Accessors(chain = true)
public class PersonDto {
    private Integer id;
    private String name;
    private String address;
    private String work;
    private Integer age;
}
