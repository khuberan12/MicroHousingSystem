package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ResidenceList extends AppCompatActivity {

    private static final String TAG = "ResidenceList";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Residence> itemList;
    private SqliteHelper sqliteHelper;
    private FloatingActionButton fab;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private Button saveButton;
    private EditText residenceAddress;
    private EditText residenceAvailable;
    private EditText residenceSize;
    private EditText residenceRental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_list);

        recyclerView = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);

        sqliteHelper = new SqliteHelper(this);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopDialog();
            }
        });
    }

    private void createPopDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        residenceAddress = view.findViewById(R.id.residenceAddress);
        residenceAvailable = view.findViewById(R.id.residenceAvailable);
        residenceSize = view.findViewById(R.id.residenceSize);
        residenceRental = view.findViewById(R.id.residenceRental);
        saveButton = view.findViewById(R.id.saveButton);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!residenceAddress.getText().toString().isEmpty()
                        && !residenceAvailable.getText().toString().isEmpty()
                        && !residenceSize.getText().toString().isEmpty()
                        && !residenceAddress.getText().toString().isEmpty()
                        && !residenceRental.getText().toString().isEmpty()) {
                    saveItem(v);
                } else {
                    Snackbar.make(v, "Empty Fields Not Allowed", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });
    }

    private void saveItem(View view) {
        //Todo: save each item to db
        Residence residence = new Residence();

        String add = residenceAddress.getText().toString().trim();
        String nu = residenceAvailable.getText().toString().trim();
        String rs = residenceSize.getText().toString().trim();
        String rental = residenceRental.getText().toString().trim();

        residence.setAddress(add);
        residence.setNumOfUnits(nu);
        residence.setSizePerUnit(rs);
        residence.setMonthlyRental(rental);

        sqliteHelper.addResidence(residence);

        Snackbar.make(view, "New Residence Created", Snackbar.LENGTH_SHORT)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //code to be run
                alertDialog.dismiss();
                //Todo: move to next screen - details screen
                startActivity(new Intent(ResidenceList.this, ResidenceList.class));
                finish();

            }
        }, 1200);// 1sec


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
        Intent i = new Intent(ResidenceList.this, SubmitApplication.class);
        startActivity(i);
    }

}
