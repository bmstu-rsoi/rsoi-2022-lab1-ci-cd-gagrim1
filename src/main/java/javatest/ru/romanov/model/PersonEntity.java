package javatest.ru.romanov.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "lab_1", name = "persons")
@Data
public class PersonEntity implements Serializable {
    private final static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "address", length = 128)
    private String address;
    @Column(name = "age", precision = 3)
    private Short age;
    @Column(name = "job", length = 128)
    private String job;
}
