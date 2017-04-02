package com.hertzdonut.hackathon2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarChooserActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner carSpinner;
    private TextView txtClass, txtDates;
    private int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_chooser);

        customer_id = getIntent().getExtras().getInt("id");

        // Get text view elements from layout
        txtClass = (TextView) findViewById(R.id.txtClass);
        txtDates = (TextView) findViewById(R.id.txtDates);

        // Set text for text views
        txtClass.setText("Midsize");
        txtDates.setText("4/3/17 - 4/10/17");

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