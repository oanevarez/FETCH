package com.example.fetch_json_excercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    public Context mContext;
    public List<List_Model> mData;

    public Adaptery(Context mContext, List<List_Model> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public Adaptery() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.list_item, parent, false); //false??
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(mData.get(position).getId()));
        holder.listID.setText(String.valueOf(mData.get(position).getListID()));
        holder.name.setText(String.valueOf(mData.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView listID;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_text);
            listID = itemView.findViewById(R.id.listID_text);
            name = itemView.findViewById(R.id.name_text);
        }
    }


}
