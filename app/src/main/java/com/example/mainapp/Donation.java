package com.example.mainapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class Donation extends AppCompatActivity {
    String donationType = "";
    private EditText name,phone,address;
    private FirebaseDatabase mDatabase ;
    private DatabaseReference myRef ;
    private ProgressDialog sProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        name = findViewById(R.id.donationName);
        phone = findViewById(R.id.donationNumber);
        address = findViewById(R.id.donationAdress);
        mDatabase =  FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Donor");
        sProgress = new ProgressDialog(this); }

    public void donation(View view) {
        sProgress.setMessage("جار الإرسال");
        sProgress.show();
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("Donation Type",donationType);
        hashMap.put("Phone",phone.getText().toString());
        hashMap.put("Address",address.getText().toString());
        hashMap.put("Name",name.getText().toString());
        myRef.push().setValue(hashMap);
        sProgress.dismiss();
    }

    public void money(View view) {
        donationType = "فلوس";
    }

    public void matrials(View view) {
        donationType = "مواد";
    }
}
