package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Method;

import static android.R.attr.start;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameField, passwordField, confirmPasswordField,
            firstNameField, lastNameField, birthDateField;
    private Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get text fields from layout
        usernameField = (EditText) findViewById(R.id.edtTxtUsername);
        passwordField = (EditText) findViewById(R.id.edtTxtPassword);
        confirmPasswordField = (EditText) findViewById(R.id.edtTxtConfirmPassword);
        firstNameField = (EditText) findViewById(R.id.edtTxtFirstName);
        lastNameField = (EditText) findViewById(R.id.edtTxtLastName);
        birthDateField = (EditText) findViewById(R.id.edtTxtBirthDate);
    }

    // Method called when submit button is clicked
    public void submit(View v) {
        // Get values from fields
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String birthDate = birthDateField.getText().toString();

        // Verify that password and confirm password match
        if(password.equals(confirmPassword)) {

        }
        else {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
        }

        // Submit user to database





        // Load Home Activity
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    // Method called when Cancel button is clicked
    public void cancel(View v) {
        Intent cancelIntent = new Intent(this, MainActivity.class);
        startActivity(cancelIntent);
    }
}