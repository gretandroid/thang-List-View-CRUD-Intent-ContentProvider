package com.example.listview.controller;

import com.example.listview.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDao {
    // stock persons ça pourrait être dans la base
    private static final List<Person> personList = new ArrayList<>();

    synchronized static public void addPerson(Person person) {
        personList.add(person);
    }

    static public List<Person> getAllPersons() {
        return Collections.unmodifiableList(personList);
    }

}
