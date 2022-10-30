package ru.bmstu.personservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Data
@Entity
@Table(name = "persons")
@Accessors(chain = true)
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "work")
    private String work;

    @Min(0)
    @Column(name = "age")
    private Integer age;
}
