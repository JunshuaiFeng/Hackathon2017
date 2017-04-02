package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

public class ReservationActivity extends AppCompatActivity {
    private TextView firstName, lastName, email, location, carClass, startDate, endDate;
    private int customer_id, reservation_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Get Reservation object from intent
        customer_id = getIntent().getExtras().getInt("id");
        reservation_id = getIntent().getExtras().getInt("reservationId");

        // Get text view elements from layout
        firstName = (TextView) findViewById(R.id.txtFirstName);
        lastName = (TextView) findViewById(R.id.txtLastName);
        email = (TextView) findViewById(R.id.txtEmail);
        location = (TextView) findViewById(R.id.txtLocation);
        carClass = (TextView) findViewById(R.id.txtCarClass);
        startDate = (TextView) findViewById(R.id.txtStartDate);
        endDate = (TextView) findViewById(R.id.txtEndDate);

        ReservationLogic logic = new ReservationLogic();
        Reservation reservation = null;
        try {
            reservation = logic.loadSingleReservation(reservation_id);
            RegisterLogic registerLogic = new RegisterLogic();
            Profile profile = registerLogic.getProfile(customer_id);
            LocationLogic locationLogic = new LocationLogic();
            Object[] obj = locationLogic.getLocations();
            Location currentLocation = null;
            for(int i = 0; i < obj.length; i++) {
                Location location = (Location) obj[i];
                if(location.id == reservation.location) {
                    currentLocation = location;
                }
            }
            firstName.setText("Name: " + profile.customer.firstName);
            lastName.setText(profile.customer.lastName);
            email.setText("Email: " + profile.customer.email);
            location.setText("Location: " + currentLocation.address);
            carClass.setText("Car class: " + reservation.carClass);
            startDate.setText("Start date: " + reservation.StartDate.substring(0, 10));
            endDate.setText("End date: " + reservation.ReturnDate.substring(0, 10));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // Method called when cancel reservation button is clicked
    public void cancelReservation(View v) {
        Intent myReservations = new Intent(this, MyReservations.class);
        myReservations.putExtra("id", customer_id);
        startActivity(myReservations);
    }
}
