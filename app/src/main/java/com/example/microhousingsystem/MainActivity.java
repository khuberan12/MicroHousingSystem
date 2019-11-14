package com.example.microhousingsystem;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    SqliteHelper sqliteHelper;
    Button btnSignup ;
    Button login;


    public void Login (View  view){

        //get value from edittext field
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        //authenticate user
        User currentApplicant = sqliteHelper.Authenticate(new User(null,username,password,




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

        usernameEditText =findViewById(R.id.username);
        passwordEditText =findViewById(R.id.password);
        login =findViewById(R.id.btnLogin);

        sqliteHelper= new SqliteHelper(this);

    }

    public void openDialog() {
        SignUpDialog signUpDialog = new SignUpDialog();
        signUpDialog.show(getSupportFragmentManager(), "Sign Up");
    }
}
