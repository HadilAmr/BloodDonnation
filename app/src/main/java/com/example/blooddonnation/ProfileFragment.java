package com.example.blooddonnation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    TextInputEditText Email,password,city,bloodgrp,phone;
    String City,Bloodgrp,Phone;
    private FirebaseAuth mAuth ;
    private FirebaseUser user;
    private Button modify;
    private DatabaseReference dbref;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment,container,false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Email=view.findViewById(R.id.Email);
        city=view.findViewById(R.id.city);
        password=view.findViewById(R.id.password);
        bloodgrp=view.findViewById(R.id.bld);
        phone=view.findViewById(R.id.number);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        modify=view.findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City = city.getText().toString();
                Bloodgrp = bloodgrp.getText().toString();
                Phone = phone.getText().toString();

                // Create a separate reference for updating data
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("user");

                if (user != null) {
                    String currentmail = user.getEmail();
                    updateRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                User user = snapshot.getValue(User.class);
                                if (user != null && user.getEmail().equals(currentmail)) {
                                    String uid = snapshot.getKey();

                                    // Use the uid to create a child reference for the specific user
                                    DatabaseReference userRef = updateRef.child(uid);

                                    // Update the specific fields
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("phone", Phone);
                                    updates.put("city", City);
                                    updates.put("bloodgrp", Bloodgrp);

                                    userRef.updateChildren(updates)
                                            .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show())
                                            .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle the error
                        }
                    });
                }
            }
        });

        dbref= FirebaseDatabase.getInstance().getReference("user");
        if (user != null) {
            String uid = user.getUid();
            String currentmail = user.getEmail();
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        User User= snapshot.getValue(User.class);
                        if((User.getEmail()).equals(currentmail)){
                            Email.setText(snapshot.child("email").getValue(String.class));
                            city.setText(snapshot.child("city").getValue(String.class));
                            bloodgrp.setText(snapshot.child("bloodgrp").getValue(String.class));
                            phone.setText(snapshot.child("phone").getValue(String.class));
                            password.setText(snapshot.child("password").getValue(String.class));
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
