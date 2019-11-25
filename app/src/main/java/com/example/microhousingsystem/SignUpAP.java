package com.example.microhousingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpAP extends AppCompatActivity {

    private Button btnBackAP;
    private Button btnSignupAP;

    EditText usernameET;
    EditText passwordET;
    EditText fullnameET;
    EditText emailET;
    EditText monthlyInconmeET;

    SqliteHelper sqliteHelper;

    public void signUp(View view) {
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String fullname = fullnameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String monthlyIncome = monthlyInconmeET.toString().trim();


        Applicant newAp = new Applicant(null, username, password, fullname, email, monthlyIncome);
        newAp.setUsername(usernameET.getText().toString().trim());
        newAp.setPassword(passwordET.getText().toString().trim());
        newAp.setFullname(fullnameET.getText().toString().trim());
        newAp.setEmail(emailET.getText().toString().trim());
        newAp.setMonthlyIncome(monthlyInconmeET.getText().toString().trim());
        newAp.getUserType();

        validate();


        if(!validate()) {
            Toast.makeText(this,"Signup has Failed",Toast.LENGTH_SHORT).show();
        }
        else {

            sqliteHelper.addApplicant(newAp);
            Toast.makeText(this, "You have been registered as Applicant", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    //validation
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
        if(emailET.length()<1 || emailET.length() > 20 ){
            emailET.setError("Please enter valid email address");
            valid = false;
        }
        if(monthlyInconmeET.length()<1 || monthlyInconmeET.length() > 15 ){
            monthlyInconmeET.setError("Please enter valid email address");
            valid = false;
        }

        return valid;
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

        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        fullnameET = findViewById(R.id.txtFullnameAP);
        emailET = findViewById(R.id.txtEmail);
        monthlyInconmeET = findViewById(R.id.txtSalary);


        sqliteHelper = new SqliteHelper(this);
    }

    public void openMain() {
        Intent i = new Intent(this, LoginAP.class);
        startActivity(i);
    }

}

