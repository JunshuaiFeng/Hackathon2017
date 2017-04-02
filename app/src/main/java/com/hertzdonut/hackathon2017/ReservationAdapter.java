package com.hertzdonut.hackathon2017;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        // Set object values
        location.setText(list.get(position).getLocation());
        dates.setText(list.get(position).getStartDate() + " - " + list.get(position).getEndDate());

        return rowView;
    };

    public void setList(ArrayList<Reservation> list) {
        this.list = list;
    }
}
