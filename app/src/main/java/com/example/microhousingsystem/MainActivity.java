package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnApplicant;
    private Button btnHo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    btnApplicant = findViewById(R.id.btnApplicant);

        btnApplicant.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openLoginAP();
        }
    });

    btnHo = findViewById(R.id.btnHo);
        btnHo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openLoginHO();
        }
    });
}

    public void openLoginAP() {
        Intent i = new Intent(this, LoginAP.class);
        startActivity(i);
    }

    public void openLoginHO() {
        Intent j = new Intent(this, LoginHO.class);
        startActivity(j);
    }

}