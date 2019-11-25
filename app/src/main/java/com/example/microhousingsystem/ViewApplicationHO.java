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

public class ViewApplicationHO extends AppCompatActivity {
    private Button btnBackho;
    ListView applicationView;
    SqliteHelper sqliteHelper;
    Application application;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_ho);

        btnBackho = findViewById(R.id.btnBackho);
        btnBackho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionHO();
            }
        });

        sqliteHelper = new SqliteHelper(ViewApplicationHO.this);
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

        GetAllApplication();

    }

    public void GetAllApplication(){

        application = null;
        List<Application> contactList = sqliteHelper.getAllApplication();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList);
        applicationView.setAdapter(adapter);
    }

    public void openOptionHO(){
        Intent i = new Intent(this,OptionHO.class);
        startActivity(i);
    }
}
