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

public class RequestAdapter2 extends RecyclerView.Adapter<RequestAdapter2.RequestViewHolder>{
    private Context context;
    private List<Request> list;
    public RequestAdapter2(Context context, List<Request> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RequestAdapter2.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestViewHolder(LayoutInflater.from(context).inflate(R.layout.global_request_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter2.RequestViewHolder holder, int position) {
        holder.contenu.setText(list.get(position).getContent());
        holder.email.setText(list.get(position).getEmail());
        holder.city.setText(list.get(position).getCity());
        holder.blood.setText(list.get(position).getBlood_type());
        holder.tel.setText(list.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void FilterList(ArrayList<Request> filterList){
        list=filterList;
        notifyDataSetChanged();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView email,tel,city,blood,contenu;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.email);
            city=itemView.findViewById(R.id.city);
            blood=itemView.findViewById(R.id.Bloodgrp);
            tel=itemView.findViewById(R.id.phone);
            contenu = itemView.findViewById(R.id.content);
        }
    }
}

