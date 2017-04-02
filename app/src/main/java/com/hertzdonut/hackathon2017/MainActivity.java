package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;

import com.estimote.sdk.SystemRequirementsChecker;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Get text fields from layout
        usernameField = (EditText) findViewById(R.id.edtTxtUsername);
        passwordField = (EditText) findViewById(R.id.edtTxtPassword);

        usernameField.setText("test@gmail.com");
        passwordField.setText("password");
    }

    // Method called when login button is clicked
    public void login(View v) throws JSONException {
        // Get username and password from text fields
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();



        // Validate user
        LoginLogic logic = new LoginLogic();
        Profile profile = logic.login(username, password);
        if(profile == null) {
            Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_LONG).show();
        }
        else {
            // Go to home activity
            Intent homeIntent = new Intent(this, HomeActivity.class);
            // Start register activity
            startActivity(homeIntent);
        }
    }

    // Method called when register button is clicked
    public void register(View v) {
        // Create intent
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        // Start register activity
        startActivity(registerIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}