package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.listview.controller.PersonDao;
import com.example.listview.model.Person;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    public static final String PERSON_KEY = "Person";
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

        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // retrieve person clicked
                Person person = PersonDao.getAll().get(position);

                // create an intent to sent message to main activity
                Intent intent = new Intent();
                intent.putExtra(PERSON_KEY, person);

                // set a code OK to send to the main activity
                setResult(RESULT_OK, intent);

                // close Second Activity, back to previous activity, i.e. MainActivity
                finish();

            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }
}