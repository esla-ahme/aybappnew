package com.example.mainapp;

import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class donationAdmin extends AppCompatActivity {
    private ListView listView;

    private ArrayList<model> arrayList;
    private ArrayList<String> arrayListKeys;
    private donationAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    Vibrator vibe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_admin);
        vibe = (Vibrator) donationAdmin.this.getSystemService(Context.VIBRATOR_SERVICE);

        listView = findViewById(R.id.listviewDonor);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Donor");
        arrayList = new ArrayList<>();
        arrayListKeys =new ArrayList<>();
        adapter = new donationAdapter(getApplicationContext(),0,arrayList);
        listView.setAdapter(adapter);


        /**/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                RemoveItem(i);
                return false;
            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String Name = dataSnapshot.child("Name").getValue().toString();
                String Phone = dataSnapshot.child("Phone").getValue().toString();
                String Address = dataSnapshot.child("Address").getValue().toString();
                String Type = dataSnapshot.child("Donation Type").getValue().toString();
                String key = dataSnapshot.getKey();
                arrayListKeys.add(key);
                arrayList.add(new model(Address,Name,Phone,Type));
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
    public void RemoveItem(int m){
        String key = arrayListKeys.get(m);
        vibe.vibrate(120);
        myRef.child(key).setValue(null);
        arrayListKeys.remove(m);
        arrayList.remove(m);
        Toast.makeText(getApplicationContext(),"message deleted", Toast.LENGTH_SHORT).show();


    }
}
