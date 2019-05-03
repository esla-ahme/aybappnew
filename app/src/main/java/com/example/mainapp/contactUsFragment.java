package com.example.mainapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class contactUsFragment extends Fragment {
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (FragmentActivity) context;
        }
    }

    private EditText Email ,msg;
    private FirebaseDatabase mDatabase ;
    private DatabaseReference myRef ;
    private ProgressDialog sProgress;
    private Button button;
    @SuppressLint("WrongViewCast")

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_contact_us,container,false);
        Email = view.findViewById(R.id.contactMail);
        msg = view.findViewById(R.id.contactMessage);
        button = view.findViewById(R.id.contactUsSend);
        mDatabase =  FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Messages");
        sProgress = new ProgressDialog(mActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        return view;

    }
    public void sendMessage() {
        sProgress.setMessage("جار الإرسال");
        sProgress.show();
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("Message",msg.getText().toString());
        hashMap.put("Email",Email.getText().toString());

        myRef.push().setValue(hashMap);
        Toast.makeText(mActivity,"تم الارسال",Toast.LENGTH_SHORT).show();
        sProgress.dismiss();

    }
}
