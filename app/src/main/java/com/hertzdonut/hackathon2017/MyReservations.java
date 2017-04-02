package com.hertzdonut.hackathon2017;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MyReservations extends AppCompatActivity {
    ReservationAdapter adapter;
    private Context context;
    private ArrayList<Reservation> list;
    private ListView listView;
    private int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        customer_id = getIntent().getExtras().getInt("id");

        context = getApplicationContext();

        // Pull array list of reservations from db

        list = new ArrayList<>();
        ReservationLogic logic = new ReservationLogic();
        try {
            Object[] res = logic.loadCustomerReservations(customer_id);

            for(int i = 0; i < res.length; i++) {
                Reservation reservation = (Reservation) res[i];
                list.add(reservation);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


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
                reservationIntent.putExtra("id", customer_id);


                Reservation reservation = (Reservation) listView.getAdapter().getItem(position);
                reservationIntent.putExtra("reservationId", reservation.id);
                startActivity(reservationIntent);
            }
        });
    }
}
