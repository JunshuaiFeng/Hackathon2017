package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
 
public class MainActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get text fields from layout
        usernameField = (EditText) findViewById(R.id.edtTxtUsername);
        passwordField = (EditText) findViewById(R.id.edtTxtPassword);
    }

    // Method called when login button is clicked
    public void login(View v) {
        // Get username and password from text fields
        String username = usernameField.getText().toString();
        String password = usernameField.getText().toString();

        // Validate user






        // Go to home activity
        Intent homeIntent = new Intent(this, HomeActivity.class);
        // Start register activity
        startActivity(homeIntent);
    }

    // Method called when register button is clicked
    public void register(View v) {
        // Create intent
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        // Start register activity
        startActivity(registerIntent);
    }
}