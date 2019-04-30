package com.example.mainapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class postsAdmin extends AppCompatActivity {
    private ListView listView;

    private ArrayList<model> arrayList;
    private ArrayList<String> arrayListKeys;
    private adapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataSnapshot dataSnapshotTemp;
    String stringTemp;
    int intTemp=0;
    Vibrator vibe;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         vibe = (Vibrator) postsAdmin.this.getSystemService(Context.VIBRATOR_SERVICE);

        listView = findViewById(R.id.listview);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");
        arrayList = new ArrayList<>();
        arrayListKeys =new ArrayList<>();
        adapter = new adapter(getApplicationContext(),0,arrayList);
        listView.setAdapter(adapter);


/**/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                RemoveItem(i);
                return false;
            }
        });

        /**/




        //show and update listview
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

//**//




    }
    public void RemoveItem(int m){
       String key = arrayListKeys.get(m);
        vibe.vibrate(80);
        myRef.child(key).setValue(null);
       arrayListKeys.remove(m);
       arrayList.remove(m);
        Toast.makeText(getApplicationContext(),"deleted", Toast.LENGTH_SHORT).show();


    }
    public void addPost(View view) {
        Intent myIntent = new Intent(postsAdmin.this, PostActivity.class);
        postsAdmin.this.startActivity(myIntent);
    }
    public void getPOSTS(View view) {
        Intent myIntent = new Intent(postsAdmin.this, posts.class);
        postsAdmin.this.startActivity(myIntent);
            }
}
