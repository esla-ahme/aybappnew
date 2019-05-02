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

public class messagesAdmin extends AppCompatActivity {
    private ListView listView;

    private ArrayList<model> arrayList;
    private ArrayList<String> arrayListKeys;
    private messageAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    Vibrator vibe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.contact_us_admin);
    vibe = (Vibrator) messagesAdmin.this.getSystemService(Context.VIBRATOR_SERVICE);

    listView = findViewById(R.id.listviewMsg);

    database = FirebaseDatabase.getInstance();
    myRef = database.getReference("Messages");
    arrayList = new ArrayList<>();
    arrayListKeys =new ArrayList<>();
    adapter = new messageAdapter(getApplicationContext(),0,arrayList);
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

            String email = dataSnapshot.child("Email").getValue().toString();
            String msg = dataSnapshot.child("Message").getValue().toString();

            String key = dataSnapshot.getKey();
            arrayListKeys.add(key);
            arrayList.add(new model(email,msg));
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


