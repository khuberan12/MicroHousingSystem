package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    SqliteHelper sqliteHelper;
    Button btnSignup;

    public void Login (View view){

        String username = usernameEditText.getText().toString();
        String Password = passwordEditText.getText().toString();

        //Authenticate user
        User currentUser = sqliteHelper.Authenticate(new User(null, username, Password));

        //Check Authentication is successful or not
        if (currentUser != null) {
            Toast.makeText(this, "Loged In", Toast.LENGTH_SHORT).show();
            //User Logged in Successfully Launch You home screen activity

        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            //User Logged in Failed
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        SignUpDialog signUpDialog = new SignUpDialog();
        signUpDialog.show(getSupportFragmentManager(), "Sign Up");
    }
}
