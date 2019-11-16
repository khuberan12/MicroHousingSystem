package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpAP extends AppCompatActivity {

    private Button btnBackAP;

    EditText usernameET;
    EditText passwordET;
    EditText fullnameET;
    EditText emailET;
    EditText monthlyInconmeET;

    SqliteHelper sqliteHelper;

    public void signUp(View view){
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String fullname = fullnameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String monthlyIncome = monthlyInconmeET.toString().trim();

        Applicant newAp = new Applicant(null,username,password,fullname,email,monthlyIncome);
        sqliteHelper.addApplicant(newAp);

        Toast.makeText(this, "You have been registered as Applicant", Toast.LENGTH_SHORT).show();
        finish();
    }


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

        usernameET =findViewById(R.id.usernameEditText);
        passwordET =findViewById(R.id.passwordEditText);
        fullnameET =findViewById(R.id.txtFullnameAP);
        emailET = findViewById(R.id.txtEmail);
        monthlyInconmeET =findViewById(R.id.txtSalary);

        sqliteHelper = new SqliteHelper(this);
    }

    public void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

