package com.example.blooddonnation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> list;

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        holder.email.setText(list.get(position).getEmail());
        holder.blood.setText(list.get(position).getBloodgrp());
        holder.city.setText(list.get(position).getCity());
        holder.phone.setText(list.get(position).getPhone());
    }
    public void FilterList(ArrayList<User> filterList){
        list=filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView email,blood,city,phone;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.email);
            city=itemView.findViewById(R.id.city);
            blood=itemView.findViewById(R.id.Bloodgrp);
            phone=itemView.findViewById(R.id.phone);

        }
    }
}
