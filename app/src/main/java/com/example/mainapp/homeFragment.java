package com.example.mainapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class homeFragment extends Fragment {
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (FragmentActivity) context;
        }
    }

    private ListView listViewUser;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList arrayList;
    private adapter adapter;
    private ArrayList<String> arrayListKeys;
    public static String  KEY;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_home,container,false);
        listViewUser = view.findViewById(R.id.listviewuser);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");

        arrayList = new ArrayList<>();
        arrayListKeys = new ArrayList<>();
        adapter = new adapter(mActivity,0,arrayList);
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

        return view;

    }
    public void openItem() {
        Intent myIntent = new Intent(mActivity, postDetails.class);
        mActivity.startActivity(myIntent);
    }
}
