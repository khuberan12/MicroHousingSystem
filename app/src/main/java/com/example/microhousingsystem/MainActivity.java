package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    FirebaseAuth mFirebaseAuth;

    Button btnSignup;

    public void Login (View  view){

        String username = usernameEditText.getText().toString();
        String Password = passwordEditText.getText().toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To view housing officer or residence
        btnSignup =  findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        passwordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty()){
                    usernameEditText.setError("Please enter email ID");
                    usernameEditText.requestFocus();
                }
                else if (password.isEmpty()){
                    passwordEditText.setError("Please enter password");
                    passwordEditText.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Field are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new )
                }
            }
        });










    }

    public void openDialog() {
        SignUpDialog signUpDialog = new SignUpDialog();
        signUpDialog.show(getSupportFragmentManager(), "Sign Up");
    }
}
