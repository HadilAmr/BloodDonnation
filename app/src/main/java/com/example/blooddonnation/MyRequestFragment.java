package com.example.blooddonnation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRequestFragment extends Fragment {
    private String contenu,email,blood,city,tel;
    private RecyclerView recyclerView;
    private TextView newrqst;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btn;
    private List<Request> list;
    private RequestAdapter adapter;
    private FirebaseAuth mAuth ;
    private FirebaseUser user;
    private DatabaseReference dbref,dbref2;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        list= new ArrayList<Request>();
        adapter = new RequestAdapter(context, list);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_my_requests,container,false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        newrqst = view.findViewById(R.id.new_request);
        btn = view.findViewById(R.id.add_request);
        recyclerView= view.findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        dbref= FirebaseDatabase.getInstance().getReference("user");
        adapter.setOnItemClickListener(new RequestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Request request) {
                int position = list.indexOf(request);
                if (position != -1) {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                    dbref2 = FirebaseDatabase.getInstance().getReference("Request");
                    dbref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Request rqst= snapshot.getValue(Request.class);
                                if((rqst.getContent()).equals(request.getContent())){
                                    snapshot.getRef().removeValue();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            }
        }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenu = newrqst.getText().toString();
                email=user.getEmail();
                if(contenu.isEmpty()){
                    newrqst.setError("Make sure to fill the content before making the request");
                }
                else{
                    dbref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                User user = dataSnapshot.getValue(User.class);
                                if (user != null && user.getEmail().equals(email)) {
                                    city = user.getCity();
                                    blood = user.getBloodgrp();
                                    tel = user.getPhone();
                                    dbref2 = FirebaseDatabase.getInstance().getReference("Request");
                                    Request request = new Request(email, tel, blood, city, contenu);
                                    dbref2.push().setValue(request);
                                    Toast.makeText(getContext(), "Request added with success !", Toast.LENGTH_SHORT).show();
                                    newrqst.setText("");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle error
                        }
                    });

                }
            }
        });
        try{dbref2 = FirebaseDatabase.getInstance().getReference("Request");
            dbref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    String currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Request request = dataSnapshot.getValue(Request.class);
                        if (request != null && request.getEmail().equals(currentUserEmail)) {
                            list.add(request);
                        }
                    }
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


        }catch (Exception ex){
            Log.d("ERR",ex.getMessage());
        }
    }
}
