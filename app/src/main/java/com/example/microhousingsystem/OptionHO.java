package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionHO extends AppCompatActivity {
    private Button btnAddResidence;
    private Button btnViewApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_ho);

        btnAddResidence = findViewById(R.id.btnViewResidence);

        btnAddResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddResidence();
            }
        });

        btnViewApp = findViewById(R.id.btnViewApp);

        btnViewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewApp();
            }
        });
    }

    public void openAddResidence() {
        Intent i = new Intent(OptionHO.this, ResidenceActivity.class);
        startActivity(i);
    }

    public void openViewApp(){
        Intent i = new Intent(OptionHO.this, ViewApplication.class);
        startActivity(i);
    }

}
