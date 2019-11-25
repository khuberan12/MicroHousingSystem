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

import java.util.List;

public class ViewApplication extends AppCompatActivity {
    private Button btnBack;
    ListView applicationView;
    SqliteHelper sqliteHelper;
    Application application;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionAp();
            }
        });

        sqliteHelper = new SqliteHelper(ViewApplication.this);
        applicationView = findViewById(R.id.applicationView);

        applicationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application = (Application) applicationView.getItemAtPosition(position);

                for(int i=0; i < applicationView.getChildCount(); i++){
                    if (position == i){
                        applicationView.getChildAt(i).setBackgroundColor(Color.parseColor("#86000000"));
                    }else{
                        applicationView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });

        GetAllContacts();

    }

    public void GetAllContacts(){

        application = null;
        List<Application> contactList = sqliteHelper.getAllApplication();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList);
        applicationView.setAdapter(adapter);
    }

    public void openOptionAp(){
        Intent i = new Intent(this,OptionAP.class);
        startActivity(i);
    }
}
