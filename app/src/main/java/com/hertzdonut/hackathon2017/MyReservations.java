package com.hertzdonut.hackathon2017;

import android.content.Context;
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

        // Set reservations in adapter
        adapter = new ReservationAdapter(context, list);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.reservationList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Load reservation



            }
        });
    }
}
