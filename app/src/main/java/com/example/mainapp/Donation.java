package com.example.mainapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Donation extends AppCompatActivity {
    String donationType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
    }

    public void donation(View view) {

    }

    public void money(View view) {
        donationType = "فلوس";
    }

    public void matrials(View view) {
        donationType = "مواد";
    }
}
