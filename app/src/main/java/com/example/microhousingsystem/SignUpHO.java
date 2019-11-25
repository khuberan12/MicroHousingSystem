package com.example.microhousingsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SignUpHO extends AppCompatActivity {
    private Button btnBackHO;

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
        validate();

        if(!validate()) {
            Toast.makeText(this,"Signup has Failed",Toast.LENGTH_SHORT).show();
        }
        else {
            sqliteHelper.addHO(newHO);
            Toast.makeText(this, "You have been registered as Housing Officer", Toast.LENGTH_SHORT).show();
            finish();

        }


    }

    public boolean validate() {
        boolean valid = true;
        if (usernameET.length() < 1 || usernameET.length() > 10) {
            usernameET.setError("Please enter less than 10 characters ");
            valid = false;
        }
        if(passwordET.length()<2){
            passwordET.setError("Please enter valid password");
            valid = false;
        }
        if (fullnameET.length() < 1 || fullnameET.length() > 20) {
            fullnameET.setError("Please enter less than 20 characters");
            valid = false;
        }

        return valid;
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

