package com.example.mainapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
    }

    public void checkLogin(View view) {

        String sUsername,sPass,ruser,rpassword;
        ruser = "admin";
        rpassword = "passwordteam4";
        sUsername = String.valueOf(username.getText());
        sPass = String.valueOf(password.getText());

        if (sUsername.equals(ruser) && sPass.equals(rpassword)){
            Intent myIntent = new Intent(loginActivity.this, Admin.class);
            loginActivity.this.startActivity(myIntent);
        }else if (sUsername != "admin" && sPass != "team4password"){
            Log.i(sUsername, "checkLogin: "+sPass);
            Toast.makeText(getApplicationContext(),"Wrong username and password",Toast.LENGTH_SHORT).show();
        }
        else if(sUsername != "admin" ){
            Toast.makeText(getApplicationContext(),"Wrong username ",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
        }
    }
}
