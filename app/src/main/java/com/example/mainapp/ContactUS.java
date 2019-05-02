package com.example.mainapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ContactUS extends AppCompatActivity {
    private EditText Email ,msg;
    private FirebaseDatabase mDatabase ;
    private DatabaseReference myRef ;
    private ProgressDialog sProgress;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Email = findViewById(R.id.contactMail);
        msg = findViewById(R.id.contactMessage);
        mDatabase =  FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Messages");
        sProgress = new ProgressDialog(this); }


    public void sendMessage(View view) {
        sProgress.setMessage("جار الإرسال");
        sProgress.show();
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("Message",msg.getText().toString());
        hashMap.put("Email",Email.getText().toString());

        myRef.push().setValue(hashMap);
        sProgress.dismiss();

    }
}
