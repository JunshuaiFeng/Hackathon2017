package com.hertzdonut.hackathon2017;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Joey Laptop on 4/1/2017.
 */

public class ReservationAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Reservation> list;
    private LayoutInflater inflater;

    public ReservationAdapter(Context context, ArrayList<Reservation> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.simplerow, null,true);

        // Get objects from layout
        TextView location = (TextView) rowView.findViewById(R.id.location);
        TextView dates = (TextView) rowView.findViewById(R.id.dates);

        LocationLogic locationLogic = new LocationLogic();
        Location currentLocation = null;
        try {
            Object[] loc = locationLogic.getLocations();
            for(int i = 0; i < loc.length; i++) {
                Location l = (Location) loc[i];
                if(l.id == list.get(position).location) {
                    currentLocation = l;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set object values
        location.setText("Location: " + currentLocation.address + ", Florida, US");
        dates.setText("Start: " + list.get(position).StartDate.substring(0,10) +
                "\nEnd: " + list.get(position).ReturnDate.substring(0,10));

        return rowView;
    };

    public void setList(ArrayList<Reservation> list) {
        this.list = list;
    }
}
