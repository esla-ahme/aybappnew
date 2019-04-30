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

public class PostDetailsAdapter extends ArrayAdapter<model> {
    ArrayList<model> modelArrayList;
    Context context;
    public PostDetailsAdapter(@NonNull Context context, int resource, @NonNull List<model> objects) {
        super(context, resource, objects);
        this.modelArrayList = modelArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        if(convertView== null){
            LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_detailed_post, null,true);
        }
        model models = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagePostUser);
        Picasso.get().load(models.getImage()).into(imageView);
        TextView post = convertView.findViewById(R.id.titlePostDetails);
        post.setText(models.getPost());
        TextView content = convertView.findViewById(R.id.contentPostDetails);
        content.setText(models.getContent());
        return convertView;
    }
}
