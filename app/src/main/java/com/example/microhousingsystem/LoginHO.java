package com.example.microhousingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginHO extends AppCompatActivity {
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
        User currentApplicant = sqliteHelper.AuthenticateHO(new HousingOfficer(null,username,password));




        //Check Authentication is successful or not
        if (currentApplicant != null) {
            Toast.makeText(this, "Logged In as House officer", Toast.LENGTH_SHORT).show();
            //User Logged in Successfully Launch You home screen activity
            startActivity(new Intent(LoginHO.this, OptionHO.class));

        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            //User Logged in Failed
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ho);

        //To view housing officer or residence
        btnSignup =  findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpHO();
            }
        });

        usernameEditText =findViewById(R.id.username);
        passwordEditText =findViewById(R.id.password);
        login =findViewById(R.id.btnLogin);

        sqliteHelper= new SqliteHelper(this);
    }

    public void openSignUpHO(){
        Intent i = new Intent(this, SignUpHO.class);
        startActivity(i);
    }
}
