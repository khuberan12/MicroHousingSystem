package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpHO extends AppCompatActivity {
    private Button btnBackHO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ho);

        btnBackHO = (Button) findViewById(R.id.btnBackHO);
        btnBackHO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }

    public void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

