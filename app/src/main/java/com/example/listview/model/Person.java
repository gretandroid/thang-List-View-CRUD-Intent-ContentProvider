package com.example.listview.model;

import java.io.Serializable;
import java.util.Objects;

// on serialise objet Person, i.e. il est converti en chaine pour être
// envoyé à l'autre activité qui va être le retransformer en objet
public class Person implements Serializable {
    private int id;
    private String surname;
    private String name;

    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public Person(int id, String surname, String name) {
        this(surname, name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
