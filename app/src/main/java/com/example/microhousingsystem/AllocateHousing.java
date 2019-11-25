package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class AllocateHousing extends AppCompatActivity {

    private Button button;
    private Button button2;
    ListView applicationView;
    SqliteHelper sqliteHelper;
    Application application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_housing);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        sqliteHelper = new SqliteHelper(AllocateHousing.this);
        applicationView = findViewById(R.id.applicationView);

        applicationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application = (Application) applicationView.getItemAtPosition(position);

                for (int i = 0; i < applicationView.getChildCount(); i++) {
                    if (position == i) {
                        applicationView.getChildAt(i).setBackgroundColor(Color.parseColor("#86000000"));
                    } else {
                        applicationView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });

        GetAllContacts();

    }

    public void GetAllContacts() {

        application = null;
        List<Application> contactList = sqliteHelper.getAllApplication();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList);
        applicationView.setAdapter(adapter);
    }

    public void accept(){
        Intent i = new Intent(AllocateHousing.this, OptionHO.class);
        startActivity(i);
        Toast.makeText(this, "Your application has been accepted.", Toast.LENGTH_SHORT).show();
    }

    public void delete(){
        Intent i = new Intent(AllocateHousing.this, OptionHO.class);
        startActivity(i);
        Toast.makeText(this, "Your application has been deleted.", Toast.LENGTH_SHORT).show();
    }
}

