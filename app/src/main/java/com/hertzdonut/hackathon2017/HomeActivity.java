package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    // Method called when Start Reservation button is clicked
    public void startReservation(View v) {
        Intent startReservation = new Intent(this, NewReservation.class);
        startActivity(startReservation);
    }

    // Method called when My Reservation buttons is clicked
    public void myReservations(View v) {
        Intent myReservation = new Intent(this, MyReservations.class);
        startActivity(myReservation);
    }

    // Method called when My Profile button is clicked
    public void myProfile(View v) {

    }
}
