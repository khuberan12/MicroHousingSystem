package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubmitApplication extends AppCompatActivity {


    Button submit;
    EditText date;
    EditText month;
    EditText year;
    SqliteHelper sqliteHelper;

    public void submitApp(View view) {

        String dateE = date.getText().toString().trim();
        String monthE = month.getText().toString().trim();
        String yearE = year.getText().toString().trim();

        Application newAp = new Application(null, dateE, monthE, yearE, "pending");
        newAp.setApplicationDate(date.getText().toString().trim());
        newAp.setRequiredMonth(month.getText().toString().trim());
        newAp.setRequiredYear(year.getText().toString().trim());

        sqliteHelper.addApplication(newAp);
        Toast.makeText(this, "Your application has been submitted", Toast.LENGTH_SHORT).show();
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application);

        submit = findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewApp();
            }
        });

        sqliteHelper = new SqliteHelper(SubmitApplication.this);
        date = findViewById(R.id.enter_date);
        month = findViewById(R.id.enter_month);
        year = findViewById(R.id.enter_year);





    }

    public void openViewApp() {
        Intent i = new Intent(this, ViewApplication.class);
        startActivity(i);
    }
}
