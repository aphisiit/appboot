package com.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by aphisit on 01-Aug-17.
 */
@Data
@Entity
@EqualsAndHashCode(of = {"id"})
public class Employee {
    private @Id
    @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String description;

    private Employee(){}

    public Employee(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}
