package com.example.listview.controller;

import com.example.listview.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDao {

    // Incremental id
    private static int incrementalId = 0;

    // stock persons ça pourrait être dans la base
    private static final List<Person> personList = new ArrayList<>();

    synchronized static public Person save(Person person) {
        if (person.getId() == 0) {
            person.setId(++incrementalId);
            personList.add(person);
        }

        return findById(person.getId()); // fake update
    }

    public static List<Person> getAll() {
        return Collections.unmodifiableList(personList);
    }

    public static Person findById(int id) {
        return personList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static boolean delete(int id) {
        return personList.removeIf(p -> p.getId() == id);
    }
}
