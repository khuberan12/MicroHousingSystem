package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubmitApplication extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    private Button btnSubmit;
    private EditText enter_date;
    private EditText enter_month;
    private EditText enter_year;

    Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application);


        sqliteHelper = new SqliteHelper(SubmitApplication.this);
        enter_date = findViewById(R.id.enter_date);
        enter_month = findViewById(R.id.enter_month);
        enter_year = findViewById(R.id.enter_year);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if (id != -1) {
            application = sqliteHelper.getApplication(id);

            enter_date.setText(application.getApplicationDate());
            enter_month.setText(application.getRequiredMonth());
            enter_year.setText(application.getRequiredYear());

            btnSubmit = findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openViewApp();
                }
            });

        }
    }

    public void openViewApp() {
        Intent i = new Intent(this, ViewApplication.class);
        startActivity(i);
        Toast.makeText(this, "Your application has been submitted", Toast.LENGTH_SHORT).show();
    }
}
