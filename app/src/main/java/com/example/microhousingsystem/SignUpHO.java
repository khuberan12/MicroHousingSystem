package com.example.microhousingsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SignUpHO extends AppCompatActivity {
    private Button btnBackHO;
    private Button btnSignupHO;

    EditText usernameET;
    EditText passwordET;
    EditText fullnameET;
    SqliteHelper sqliteHelper;

    public void signUpHO(View view){
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String fullname = fullnameET.getText().toString().trim();



        HousingOfficer newHO =  new HousingOfficer(null,username,password,fullname);
        newHO.setUsername(usernameET.getText().toString().trim());
        newHO.setPassword(passwordET.getText().toString().trim());
        newHO.setFullname(fullnameET.getText().toString().trim());

        sqliteHelper.addHO(newHO);

        Toast.makeText(this, "You have been registered as Housing Officer", Toast.LENGTH_SHORT).show();
        finish();

    }

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

        usernameET = findViewById(R.id.txtUsernameHO);
        passwordET = findViewById(R.id.txtpasswordHO);
        fullnameET =findViewById(R.id.txtFullnameHO);

        sqliteHelper = new SqliteHelper(this);




    }

    public void openMain() {
        Intent i = new Intent(this, LoginHO.class);
        startActivity(i);
    }

}

