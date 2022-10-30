package ru.bmstu.personservice.handler;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Error {
    private String message;
    private String description;
}
