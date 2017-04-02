package com.hertzdonut.hackathon2017;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyReservations extends AppCompatActivity {
    ReservationAdapter adapter;
    private Context context;
    private ArrayList<Reservation> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        context = getApplicationContext();

        // Pull array list of reservations from db

        list = new ArrayList<>();
        Reservation res1 = new Reservation("John", "Smith", "jsmith@gmail.com", "4/12/90",
                "S462-78-297", "FL", "Fort Myers International Airport", "Midsize", "4/8/17", "4/14/17", true);
        Reservation res2 = new Reservation("Jane", "Doe", "jdoe@gmail.com", "6/15/65",
                "S763-98-596", "FL", "Ft. Myers - South Tamiami Trail HLE", "Compact", "5/7/17", "6/1/17", true);
        list.add(res1);
        list.add(res2);

        // Set reservations in adapter
        adapter = new ReservationAdapter(context, list);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.reservationList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Load reservation page
                Intent reservationIntent = new Intent(context, ReservationActivity.class);
                reservationIntent.putExtra("reservation", list.get(position));
                startActivity(reservationIntent);


            }
        });
    }
}
