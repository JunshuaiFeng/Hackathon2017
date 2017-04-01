package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameField, passwordField, confirmPasswordField,
            firstNameField, lastNameField, birthDateField;
    private Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameField = (EditText) findViewById(R.id.edtTxtUsername);
        passwordField = (EditText) findViewById(R.id.edtTxtPassword);
        confirmPasswordField = (EditText) findViewById(R.id.edtTxtConfirmPassword);
        firstNameField = (EditText) findViewById(R.id.edtTxtFirstName);
        lastNameField = (EditText) findViewById(R.id.edtTxtLastName);
        birthDateField = (EditText) findViewById(R.id.edtTxtBirthDate);

        submitButton = (Button) findViewById(R.id.btnSubmit);
        cancelButton = (Button) findViewById(R.id.btnCancel);
    }

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

        // Submit user to database






    }

    public void cancel(View v) {
        Intent cancelIntent = new Intent(this, MainActivity.class);
        startActivity(cancelIntent);
    }
}
