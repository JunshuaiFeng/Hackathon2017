package com.hertzdonut.hackathon2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class CarChooserActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner carSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_chooser);

        // Get spinner element from layout
        carSpinner = (Spinner) findViewById(R.id.spinnerCar);

        // Set spinner click listeners
        carSpinner.setOnItemSelectedListener(this);

        // Set spinner Drop down elements for carSpinner
        List<String> cars = new ArrayList<String>();
        cars.add("Toyota Corolla");
        cars.add("Nissan Versa");
        cars.add("Ford Focus");
        cars.add("Nissan Altima");
        cars.add("Ford Fusion");

        // Create adapter for spinner
        ArrayAdapter<String> carAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cars);

        // Set drop down layout style for spinner
        carAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching adapters to spinner
        carSpinner.setAdapter(carAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // Method called when the submit button is clicked
    public void submit(View v) {

    }
}