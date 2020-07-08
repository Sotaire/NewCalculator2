package com.example.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapter extends RecyclerView.Adapter<MainViewHolder> {

    public void History (String str){
        elemenets.add(" " +  str);
        notifyDataSetChanged();
    }

    ArrayList<String> elemenets = new ArrayList<>();
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder,parent,false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.fill(elemenets.get(position));
    }

    @Override
    public int getItemCount() {
        return elemenets.size();
    }
}
