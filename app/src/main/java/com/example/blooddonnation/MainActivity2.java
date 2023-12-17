package com.example.blooddonnation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String email,city,grp;
    TextView mail,City,Grp;
    private DrawerLayout drawer;
    private FirebaseAuth mAuth ;
    private FirebaseUser user;
    private DatabaseReference dbref;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        dbref= FirebaseDatabase.getInstance().getReference("user");
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        if (user != null) {
            String uid = user.getUid();
            String currentmail = user.getEmail();
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        User User= snapshot.getValue(User.class);
                        if((User.getEmail()).equals(currentmail)){
                            rootView = findViewById(R.id.nav_view);
                            mail=rootView.findViewById(R.id.mail);
                            City=rootView.findViewById(R.id.city);
                            Grp=rootView.findViewById(R.id.blood);
                            mail.setText(snapshot.child("email").getValue(String.class));
                            City.setText(snapshot.child("city").getValue(String.class));
                            Grp.setText(snapshot.child("bloodgrp").getValue(String.class));
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MainActivity()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
        super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainActivity()).commit();
        }
        else if(item.getItemId() == R.id.nav_my_requests){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MyRequestFragment()).commit();
        }
        else if(item.getItemId() == R.id.nav_requests){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RequestsFragment()).commit();
        }
        else if(item.getItemId() == R.id.nav_Disconnect){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity2.this,LoginPage.class));
        }
        else if(item.getItemId() == R.id.nav_near_me){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MapFragment()).commit();
        }
        else if(item.getItemId() == R.id.nav_profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}