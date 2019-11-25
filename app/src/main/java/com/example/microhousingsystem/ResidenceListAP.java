package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ResidenceListAP extends AppCompatActivity {

    private static final String TAG = "ResidenceList";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Residence> itemList;
    private SqliteHelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_list_ap);

        recyclerView = findViewById(R.id.recyclerview);

        sqliteHelper = new   SqliteHelper(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();

        // Get items from db
        itemList = sqliteHelper.getAllResidence();

        for (Residence item : itemList) {
            Log.d(TAG, "onCreate: " + item.getAddress());
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, itemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
    public void Submit(View view) {
        RelativeLayout RelativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayout);
        RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubmit();
            }
        });
    }

    public void openSubmit() {
        Intent i = new Intent(ResidenceListAP.this, SubmitApplication.class);
        startActivity(i);
    }


}



