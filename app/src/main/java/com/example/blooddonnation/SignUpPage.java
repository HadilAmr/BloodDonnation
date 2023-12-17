package com.example.blooddonnation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {
    String Id,City,Password,Mobile,Category;
    private EditText id,city,pwd,phone;
    Spinner bloodgrp;
    private Button submitbtn;
    private TextView signin;
    private FirebaseAuth auth;
    private DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        id=findViewById(R.id.Email);
        city=findViewById(R.id.city);
        bloodgrp=findViewById(R.id.blood_group);
        pwd=findViewById(R.id.bld);
        phone=findViewById(R.id.number);
        submitbtn=findViewById(R.id.modify);
        signin=findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        String [] items= new String[]{"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodgrp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));
        bloodgrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category = bloodgrp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id = id.getText().toString();
                City = city.getText().toString();
                Password = pwd.getText().toString();
                Mobile = phone.getText().toString();
                if(Id.isEmpty()){
                    id.setError("Make sure you enter your email!");
                    id.requestFocus();
                }
                else if(City.isEmpty()){
                    city.setError("Make sure you enter your city!");
                    city.requestFocus();
                }
                else if(bloodgrp.getSelectedItem()==null){
                    Toast.makeText(SignUpPage.this, "Make sure to choose your blood group", Toast.LENGTH_SHORT).show();
                    bloodgrp.requestFocus();
                }
                else if(Password.isEmpty()){
                    pwd.setError("Make sure you enter your password!");
                    pwd.requestFocus();
                }
                else if(Mobile.isEmpty()){
                    phone.setError("Make sure you enter your phone number!");
                    phone.requestFocus();
                }
                else {
                    auth.createUserWithEmailAndPassword(Id, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String currentUserId = task.getResult().getUser().getUid();
                                dbref = FirebaseDatabase.getInstance().getReference("user").child(currentUserId);
                                insertData();
                            } else {
                                Toast.makeText(SignUpPage.this, "Error while creating the donor: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                id.setText("");
                                city.setText("");
                                pwd.setText("");
                                phone.setText("");
                            }
                        }
                    });
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this,LoginPage.class));
            }
        });
    }
    private void insertData() {
        User user = new User(Id, Mobile, Password, City, Category);
        dbref.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(SignUpPage.this, MainActivity2.class));
                    Toast.makeText(SignUpPage.this, "Donor added with success!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Log the error details
                    Exception exception = task.getException();
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                    Toast.makeText(SignUpPage.this, "Error adding donor: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}