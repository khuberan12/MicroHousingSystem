package com.example.microhousingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAP extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    SqliteHelper sqliteHelper;
    Button btnSignup ;
    Button login;


    public void Login1 (View view){


        //get value from edittext field
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();



        //authenticate user
        User currentApplicant = sqliteHelper.Authenticate(new Applicant(null,username,password));




        //Check Authentication is successful or not
        if (currentApplicant != null) {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            //User Logged in Successfully Launch You home screen activity
            startActivity(new Intent(LoginAP.this, OptionAP.class));


        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            //User Logged in Failed
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ap);

        //To view housing officer or residence
        btnSignup =  findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpAP();
            }
        });

        usernameEditText =findViewById(R.id.username);
        passwordEditText =findViewById(R.id.password);
        login =findViewById(R.id.btnLogin);

        sqliteHelper= new SqliteHelper(this);

    }

    public void openSignUpAP() {
        Intent i = new Intent(this, SignUpAP.class);
        startActivity(i);
    }
}
