package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.listview.controller.PersonDao;
import com.example.listview.model.Person;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public static final String PERSON_LIST_KEY = "PersonList";

    private EditText surnameEditText;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to UI components
        surnameEditText = findViewById(R.id.surnameEditText);
        nameEditText = findViewById(R.id.nameEditText);

    }

    public void onClickSubmit(View view) {
        // get info from UI
        String surname = surnameEditText.getText().toString();
        String name = nameEditText.getText().toString();

        // add to temporal stock, id sera ajouté lors que
        // rajoute dans la db
        Person person = new Person(surname, name);
        person.setId(1); // 1 est la valeur default
        PersonDao.addPerson(person);

        // Crée intent (message) pour demarrer second activity
        Intent intent = new Intent(this, SecondActivity.class);

        // on stock persons dans intent à envoyer SecondActivity
        intent.putExtra(PERSON_LIST_KEY, (Serializable) PersonDao.getAllPersons());

        // start second activity with message
        startActivity(intent);
    }
}