package com.example.mainapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class messageAdapter extends ArrayAdapter<model> {
    ArrayList<model> modelArrayList;
    Context context;



    public messageAdapter(@NonNull Context context, int resource, @NonNull List<model> objects) {
        super(context, resource, objects);
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView== null){
            LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_messages, null,true);
        }
        model models = getItem(position);

        TextView email = convertView.findViewById(R.id.messageEmail);
        email.setText(models.getPost());
        TextView msg = convertView.findViewById(R.id.messageMessage);
        msg.setText(models.getPhone());

        return convertView;    }
}

