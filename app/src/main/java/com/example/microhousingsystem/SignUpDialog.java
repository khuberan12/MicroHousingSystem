package com.example.microhousingsystem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SignUpDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sign Up")
                .setMessage("Are you a Housing Officer or an Applicant?")
                .setPositiveButton("Applicant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent x = new Intent(SignUpDialog.this.getActivity(), SignUpAP.class);
                        startActivity(x);

                    }
                })
                .setNegativeButton("Housing Officer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent y = new Intent(SignUpDialog.this.getActivity(), SignUpHO.class);
                        startActivity(y);
                    }
                });


        return builder.create();
    }
}
