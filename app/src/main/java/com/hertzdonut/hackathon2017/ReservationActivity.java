package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReservationActivity extends AppCompatActivity {
    private TextView firstName, lastName, email, location, carClass, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Get Reservation object from intent
        Intent i = getIntent();
        Reservation res = (Reservation) i.getExtras().getSerializable("reservation");

        // Get text view elements from layout
        firstName = (TextView) findViewById(R.id.txtFirstName);
        lastName = (TextView) findViewById(R.id.txtLastName);
        email = (TextView) findViewById(R.id.txtEmail);
        location = (TextView) findViewById(R.id.txtLocation);
        carClass = (TextView) findViewById(R.id.txtCarClass);
        startDate = (TextView) findViewById(R.id.txtStartDate);
        endDate = (TextView) findViewById(R.id.txtEndDate);

        // Set text for each text view from the Reservation object
        firstName.setText(res.getFirstName());
        lastName.setText(res.getLastName());
        email.setText(res.getEmail());
        location.setText(res.getLocation());
        carClass.setText(res.getCarClass());
        startDate.setText(res.getStartDate());
        endDate.setText(res.getEndDate());
    }

    // Method called when cancel reservation button is clicked
    public void cancelReservation(View v) {

    }
}
