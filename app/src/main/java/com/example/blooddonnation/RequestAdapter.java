package com.example.blooddonnation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequetViewHolder>{
    private Context context;
    private List<Request> list;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Request request);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public RequestAdapter(Context context, List<Request> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RequestAdapter.RequetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestAdapter.RequetViewHolder(LayoutInflater.from(context).inflate(R.layout.request_item_layout,parent,false));}

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequetViewHolder holder, int position) {
        holder.content.setText(list.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(list.get(adapterPosition));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RequetViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        public RequetViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }
    }
}
