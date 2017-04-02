package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Method;

public class MyProfileActivity extends AppCompatActivity {
    private EditText emailField, passwordField, firstNameField, lastNameField, birthDateField, licenseField, licenseStateField;
    private int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        customer_id = getIntent().getExtras().getInt("id");

        // Get elements from layout
        emailField = (EditText) findViewById(R.id.edtTxtUsername);
        passwordField = (EditText) findViewById(R.id.edtTxtPassword);
        firstNameField = (EditText) findViewById(R.id.edtTxtFirstName);
        lastNameField = (EditText) findViewById(R.id.edtTxtLastName);
        birthDateField = (EditText) findViewById(R.id.edtTxtBirthDate);
        licenseField = (EditText) findViewById(R.id.edtTxtDriversLicense);
        licenseStateField = (EditText) findViewById(R.id.edtTxtLicenseState);

        RegisterLogic logic = new RegisterLogic();
        Profile profile = logic.getProfile(customer_id);

        emailField.setText(profile.customer.email);
        passwordField.setText(profile.customer.password);
        firstNameField.setText(profile.customer.firstName);
        lastNameField.setText(profile.customer.lastName);
        birthDateField.setText(profile.customer.birthDate);
        licenseField.setText(profile.licenseNum);
        licenseStateField.setText(profile.licenseState);

    }

    // Method called when submit button is clicked
    public void submit(View v) {

    }

    // Method called when cancel button is clicked
    public void cancel(View v) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }
}
