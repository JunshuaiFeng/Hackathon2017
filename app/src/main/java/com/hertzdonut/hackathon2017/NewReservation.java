package com.hertzdonut.hackathon2017;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewReservation extends AppCompatActivity implements OnItemSelectedListener {
    private EditText firstNameField, lastNameField, emailField, birthDateField,
            licenseField, licenseStateField;
    private Button submitBtn, cancelBtn, startDateBtn, endDateBtn;
    private Spinner locationSpinner, classSpinner;
    private CheckBox checkBox;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        customer_id = getIntent().getExtras().getInt("id");
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Get text fields from layout
        firstNameField = (EditText) findViewById(R.id.edtTxtFirstName);
        lastNameField = (EditText) findViewById(R.id.edtTxtLastName);
        emailField = (EditText) findViewById(R.id.edtTxtEmail);
        birthDateField = (EditText) findViewById(R.id.edtTxtBirthDate);
        licenseField = (EditText) findViewById(R.id.edtTxtDriversLicense);
        licenseStateField = (EditText) findViewById(R.id.edtTxtLicenseState);

        // Get checkBox element from layout
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        // Get button elements from layout
        startDateBtn = (Button) findViewById(R.id.btnStartDate);
        endDateBtn = (Button) findViewById(R.id.btnEndDate);
        submitBtn = (Button) findViewById(R.id.btnSubmit);
        cancelBtn = (Button) findViewById(R.id.btnCancel);

        // Get spinner elements from layout
        locationSpinner = (Spinner) findViewById(R.id.spinnerLocation);
        classSpinner = (Spinner) findViewById(R.id.spinnerClass);

        RegisterLogic registerLogic = new RegisterLogic();
        Profile profile = registerLogic.getProfile(customer_id);

        firstNameField.setText(profile.customer.getFirstName());
        lastNameField.setText(profile.customer.getLastName());
        emailField.setText(profile.customer.getEmail());
        birthDateField.setText(profile.customer.getBirthDate());
        if(profile.licenseNum != "") {
            licenseField.setText(profile.licenseNum);
        }
        if(profile.licenseState != "") {
            licenseStateField.setText(profile.licenseState);
        }



        /*
        *   The following section sets up the drop down (spinner) menus
        *
        */

        // Set spinner click listeners
        locationSpinner.setOnItemSelectedListener(this);
        classSpinner.setOnItemSelectedListener(this);

        // Set spinner Drop down elements for locationSpinner
        ArrayList<String> array = new ArrayList<>();
        LocationLogic logic = new LocationLogic();
        try {
            Object[] obj = logic.getLocations();
            for(int i = 0; i < obj.length; i++) {
                Location location = (Location) obj[i];
                array.add(location.address);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Set spinner Drop down elements for classSpinner
        List<String> classes = new ArrayList<String>();
        classes.add("Compact");
        classes.add("Economy");
        classes.add("Full Size");
        classes.add("Midsize");
        classes.add("SUV");

        // Creating adapters for spinners
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);

        // Set drop down layout style for spinners
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching adapters to spinners
        locationSpinner.setAdapter(locationAdapter);
        classSpinner.setAdapter(classAdapter);



        /*
        *   The following section deals with the calendar for setting dates
        *
        */

        // Set calendar instance for date pickers
        calendar = Calendar.getInstance();

        // Get current date
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // Set current date for start date button
        startDateBtn.setText(new StringBuilder().append(month + 1).append("/")
                .append(day).append("/").append(year));

        // Set current date for end date button
        endDateBtn.setText(new StringBuilder().append(month + 1).append("/")
                .append(day).append("/").append(year));
    }

    // Called when drop down items are selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    // Called when start date button is clicked
    @SuppressWarnings("deprecation")
    public void setStartDate(View view) {
        // Display calendar dialog
        showDialog(999);
    }

    // Called when end date button is clicked
    @SuppressWarnings("deprecation")
    public void setEndDate(View view) {
        // Display calendar dialog
        showDialog(888);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    startDateListener, year, month, day);
        }
        else if(id == 888) {
            return new DatePickerDialog(this,
                    endDateListener, year, month, day);
        }
        return null;
    }

    // Sets date for start date
    private DatePickerDialog.OnDateSetListener startDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    startDateBtn.setText(new StringBuilder().append(arg2 + 1).append("/")
                            .append(arg3).append("/").append(arg1));
                }
            };

    // Sets date for end date
    private DatePickerDialog.OnDateSetListener endDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    endDateBtn.setText(new StringBuilder().append(arg2 + 1).append("/")
                            .append(arg3).append("/").append(arg1));
                }
            };

    // Method called when submit button is clicked
    public void submit(View v) throws JSONException {
        // Get text from fields
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = emailField.getText().toString();
        String birthDate = birthDateField.getText().toString();
        String driverLicense = licenseField.getText().toString();
        String licenseState = licenseStateField.getText().toString();
        String location = locationSpinner.getSelectedItem().toString();
        String carClass = classSpinner.getSelectedItem().toString();
        String startDate = startDateBtn.getText().toString();
        String endDate = endDateBtn.getText().toString();
        Boolean checked = checkBox.isChecked();

        // Check if box is checked
        if(!checked) {
            Toast.makeText(this, "You must agree to terms and conditions!", Toast.LENGTH_LONG).show();
        }
        else {
            // Submit to database
            ReservationLogic logic = new ReservationLogic();
            LocationLogic locationLogic = new LocationLogic();
            Object[] locations = locationLogic.getLocations();
            int locationId = -1;

            for(int i = 0; i < locations.length; i++) {
                Location loc = (Location) locations[i];
                if(loc.address.equals(location)) {
                    locationId = loc.id;
                }
            }

            boolean successful = logic.createReservation(locationId, customer_id, carClass, startDate, endDate);

            if(successful) {
                // Load home activity
                Intent homeIntent = new Intent(this, HomeActivity.class);
                homeIntent.putExtra("id", customer_id);
                startActivity(homeIntent);
            } else {
                Toast.makeText(this, "An error occurred!", Toast.LENGTH_LONG).show();
            }
        }

    }

    // Method called when cancel button is clicked
    public void cancel(View v) {
        // Loads Home Activity
        Intent cancelIntent = new Intent(this, HomeActivity.class);
        cancelIntent.putExtra("id", customer_id);
        startActivity(cancelIntent);
    }
}
