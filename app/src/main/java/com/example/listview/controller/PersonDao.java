package com.example.listview.controller;
import static com.example.listview.contentprovider.ContractProvider.*;
import static com.example.listview.contentprovider.ContractProvider.Person.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.listview.contentprovider.ContractProvider;
import com.example.listview.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDao {

    // Incremental id
    private static int incrementalId = 0;

    // stock persons ça pourrait être dans la base
    private static final List<Person> personList = new ArrayList<>();

    // create corresponding methods with content provider
    synchronized static public Person save(Person person, Context activity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PERSON_SURNAME, person.getSurname());
        contentValues.put(COL_PERSON_NAME, person.getName());

        if (person.getId() == 0) { // insert
            Uri returnedUri = activity.getContentResolver().insert(URI_PERSON, contentValues);
            person.setId(Integer.parseInt(returnedUri.getLastPathSegment()));
        }
        else { // update
            activity.getContentResolver().update(Uri.parse(URI_PERSON.getPath() + "/" + person.getId()), contentValues, null, null);
        }
        return person; // reload from base
    }

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

    @SuppressLint("Range")
    public static List<Person> getAll(Context activity) {
        List<Person> allPersons = new ArrayList<>();
        Cursor cursor = activity.getContentResolver().query(URI_PERSON,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            String result = null;
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_PERSON_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_PERSON_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(COL_PERSON_SURNAME));
                result = new StringBuilder()
                        .append(COL_PERSON_ID + " : ")
                        .append(id + " ")
                        .append(COL_PERSON_NAME + " : ")
                        .append(name + " ")
                        .append(COL_PERSON_SURNAME + " : ")
                        .append(surname + " ")
                        .toString();
                Log.d("App", result);
                allPersons.add(new Person(id, surname, name));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return allPersons;
    }

    @SuppressLint("Range")
    public static Person findById(int id, Context activity) {
        Cursor cursor = activity.getContentResolver().query(Uri.parse(URI_PERSON.getPath() + "/" + id),
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            String result = null;
                int returnedId = cursor.getInt(cursor.getColumnIndex(COL_PERSON_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_PERSON_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(COL_PERSON_SURNAME));
                result = new StringBuilder()
                        .append(COL_PERSON_ID + " : ")
                        .append(id + " ")
                        .append(COL_PERSON_NAME + " : ")
                        .append(name + " ")
                        .append(COL_PERSON_SURNAME + " : ")
                        .append(surname + " ")
                        .toString();
                Log.d("App", result);
            cursor.close();
            return new Person(returnedId, surname, name);
        }

        return null;
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

    public static int delete(int id, Context activity) {
        return activity.getContentResolver().delete(Uri.parse(URI_PERSON.getPath() + "/" + id), null, null);
    }
}
