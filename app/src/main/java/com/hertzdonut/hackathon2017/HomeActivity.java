package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        id = getIntent().getExtras().getInt("id");

        Toast.makeText(this, "User id: " + id, Toast.LENGTH_LONG).show();
    }

    // Method called when Start Reservation button is clicked
    public void startReservation(View v) {
        Intent startReservation = new Intent(this, NewReservation.class);
        startReservation.putExtra("id", id);
        startActivity(startReservation);
    }

    // Method called when My Reservation buttons is clicked
    public void myReservations(View v) {
        Intent myReservation = new Intent(this, MyReservations.class);
        myReservation.putExtra("id", id);
        startActivity(myReservation);
    }

    // Method called when My Profile button is clicked
    public void myProfile(View v) {
        Intent myProfile = new Intent(this, MyProfileActivity.class);
        myProfile.putExtra("id", id);
        startActivity(myProfile);
    }
}
