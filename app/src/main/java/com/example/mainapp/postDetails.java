package com.example.mainapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class postDetails extends AppCompatActivity {
    private ListView listView;

    private ArrayList<model> arrayList;
    private ArrayList<String> arrayListKeys;
    private com.example.mainapp.PostDetailsAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        listView = findViewById(R.id.postDetails);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");

        arrayList = new ArrayList<>();
        arrayListKeys = new ArrayList<>();
        adapter = new PostDetailsAdapter(getApplicationContext(),0,arrayList);
        listView.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String k = posts.KEY;
                if (k == dataSnapshot.getKey()){
                String photos = dataSnapshot.child("photo").getValue().toString();
                String posts = dataSnapshot.child("post").getValue().toString();
                String content = dataSnapshot.child("content").getValue().toString();
                arrayList.add(new model(posts,content,photos));
                adapter.notifyDataSetChanged();}


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





    public void goToForm(View view) {
        Intent myIntent = new Intent(postDetails.this, Donation.class);
        postDetails.this.startActivity(myIntent);
    }


    public void goToContactUs(View view) {
        Intent myIntent = new Intent(postDetails.this, ContactUS.class);
       postDetails.this.startActivity(myIntent);
    }
}
