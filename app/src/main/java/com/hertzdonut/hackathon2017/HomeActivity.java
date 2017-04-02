package com.hertzdonut.hackathon2017;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private boolean beaconNotificationsEnabled = false;
    private boolean entered = false;
    private int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        customer_id = getIntent().getExtras().getInt("id");

        beaconManager = new BeaconManager(getApplicationContext());

        // add this below:
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {


            // when user enter beacon's region
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                if(!beaconNotificationsEnabled) {
                    showNotification(
                            "Hertz Notification",
                            "Welcome to Hertz.  "
                             + "Please tap here to finish the check in process. Thank you.");
                    beaconNotificationsEnabled = true;
                    entered = true;
                }
            }

            // When user exit beacon's region
            @Override
            public void onExitedRegion(Region region) {
                if(entered) {
                    showNotification(
                            "Hertz Notification",
                            "Thank you for choosing Hertz! "
                                    + "Drive safe :)");
                    entered = false;
                    beaconNotificationsEnabled = false;
                }
            }
        });

        // connect to beacon
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        3138, 64033));
            }
        });
    }

    // Method called when Start Reservation button is clicked
    public void startReservation(View v) {
        Intent startReservation = new Intent(this, NewReservation.class);
        startReservation.putExtra("id", customer_id);
        startActivity(startReservation);
    }

    // Method called when My Reservation buttons is clicked
    public void myReservations(View v) {
        Intent myReservation = new Intent(this, MyReservations.class);
        myReservation.putExtra("id", customer_id);
        startActivity(myReservation);
    }

    // Method called when My Profile button is clicked
    public void myProfile(View v) {
        Intent myProfile = new Intent(this, MyProfileActivity.class);
        myProfile.putExtra("id", customer_id);
        startActivity(myProfile);
    }

    // Notification Message
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MyReservations.class);
        notifyIntent.putExtra("id", customer_id);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo_hertz)
                .setPriority(Notification.PRIORITY_MAX)
                .setColor(310)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
