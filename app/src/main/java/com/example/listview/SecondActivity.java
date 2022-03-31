package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.listview.model.Person;

import java.io.Serializable;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    // UI
    private ListView personListView;
    // define adapter pour list view
    private ListAdapter personListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // reference to UI element
        personListView = findViewById(R.id.personListView);

        // get intent message sent by main activity
        Intent intent = getIntent();
        List<Person> persons = (List<Person>) intent.getSerializableExtra(MainActivity.PERSON_LIST_KEY);

        // create Adapter
        personListViewAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, persons);
        personListView.setAdapter(personListViewAdapter);
    }
}