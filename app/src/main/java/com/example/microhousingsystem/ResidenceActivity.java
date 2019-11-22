package com.example.microhousingsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ResidenceActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText address;
    private EditText numOfunit;
    private EditText sizeOfUnit;
    private EditText monthlyRental;
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_activity);

        sqliteHelper = new SqliteHelper(this);

        byPassActivity();

        //check if item was saved
        List<Residence> residences = sqliteHelper.getAllResidence();
        for (Residence residence : residences) {
            Log.d("Main", "onCreate: " + residence.getAddress());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createPopupDialog();
            }
        });
    }

    private void byPassActivity() {
        if (sqliteHelper.getResidenceCount() > 0) {
            startActivity(new Intent(ResidenceActivity.this, ResidenceList.class));
            finish();
        }
    }

    private void saveItem(View view) {
        //Todo: save each item to db
        Residence item = new Residence();

        String add = address.getText().toString().trim();
        String nu = numOfunit.getText().toString().trim();
        String su = sizeOfUnit.getText().toString().trim();
        String mr = monthlyRental.getText().toString().trim();


        item.setAddress(add);
        item.setNumOfUnits(nu);
        item.setSizePerUnit(su);
        item.setMonthlyRental(mr);

        sqliteHelper.addResidence(item);

        Snackbar.make(view, "New Residence Created", Snackbar.LENGTH_SHORT)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //code to be run
                dialog.dismiss();
                //Todo: move to next screen - details screen
                startActivity(new Intent(ResidenceActivity.this, ResidenceList.class));

            }
        }, 1200);// 1sec
    }

    private void createPopupDialog() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);

        address = view.findViewById(R.id.residenceAddress);
        numOfunit = view.findViewById(R.id.residenceAvailable);
        sizeOfUnit = view.findViewById(R.id.residenceSize);
        monthlyRental = view.findViewById(R.id.residenceRental);
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!address.getText().toString().isEmpty()
                        && !numOfunit.getText().toString().isEmpty()
                        && !sizeOfUnit.getText().toString().isEmpty()
                        && !monthlyRental.getText().toString().isEmpty()) {
                    saveItem(v);
                } else {
                    Snackbar.make(v, "Empty Fields not Allowed", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        builder.setView(view);
        dialog = builder.create();// creating our dialog object
        dialog.show();// important step!
    }

}