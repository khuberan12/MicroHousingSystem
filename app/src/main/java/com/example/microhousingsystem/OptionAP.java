package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionAP extends AppCompatActivity {
    private Button btnAddResidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_ho);

        btnAddResidence = findViewById(R.id.btnAddResidence);

        btnAddResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddResidence();
            }
        });
    }

    public void openAddResidence(){
        Intent i = new Intent(OptionAP.this, ResidenceActivity.class);
        startActivity(i);
    }

}