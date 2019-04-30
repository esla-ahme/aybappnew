package com.example.mainapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class posts extends AppCompatActivity {

    private ListView listViewUser;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList arrayList;
    private adapter adapter;
    private ArrayList<String> arrayListKeys;
    public static String  KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
   listViewUser = findViewById(R.id.listviewuser);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");

        arrayList = new ArrayList<>();
        arrayListKeys = new ArrayList<>();
        adapter = new adapter(getApplicationContext(),0,arrayList);
        listViewUser.setAdapter(adapter);
        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openItem();
                KEY = arrayListKeys.get(i);

            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String photos = dataSnapshot.child("photo").getValue().toString();
                String posts = dataSnapshot.child("post").getValue().toString();
                String key = dataSnapshot.getKey();
                arrayListKeys.add(key);
                arrayList.add(new model(posts,photos));
                adapter.notifyDataSetChanged();


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

    public void openItem() {
        Intent myIntent = new Intent(posts.this, postDetails.class);
        posts.this.startActivity(myIntent);
    }



}