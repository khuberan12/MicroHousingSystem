package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpAP extends AppCompatActivity {

    private Button btnBackAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ap);

        btnBackAP = (Button) findViewById(R.id.btnBackAP);
        btnBackAP.setOnClickListener(new View.OnClickListener() {
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

