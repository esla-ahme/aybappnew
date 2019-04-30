package com.example.mainapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class donationAdapter extends ArrayAdapter<model> {
    ArrayList<model> modelArrayList;
    Context context;

    public donationAdapter(@NonNull Context context, int resource, @NonNull List<model> objects) {
        super(context, resource, objects);
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView== null){
            LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_donation, null,true);
        }
        model models = getItem(position);

        TextView name = convertView.findViewById(R.id.donorName);
        name.setText(models.getName());
        TextView phone = convertView.findViewById(R.id.donorPhone);
        phone.setText(models.getPhone());
        TextView address = convertView.findViewById(R.id.donorAddress);
        address.setText(models.getAddress());
        TextView type = convertView.findViewById(R.id.donorType);
        type.setText(models.getType());
        return convertView;    }
}
