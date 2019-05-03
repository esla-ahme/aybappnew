package com.example.mainapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class UserInterface extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_interface);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new homeFragment()).commit();
        navigationView.setCheckedItem(R.id.menuHome);}
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new homeFragment()).commit();
                break;
            case R.id.menuAin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ainFragment()).commit();
                break;
            case R.id.menuContactUS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new contactUsFragment()).commit();
                break;
           case R.id.menuAbout:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new aboutFragment()).commit();
               break;
            case R.id.menuLogin:
                Intent myIntent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(myIntent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
