package com.abdo.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.tmdb.R;

import java.util.ArrayList;

public class RecyclerTextAdapter extends RecyclerView.Adapter<RecyclerTextAdapter.Holder> {


    private ArrayList<String> list;
    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.txt.setText(list.get(position));
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView  txt;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txt  = itemView.findViewById(R.id.ttxt);

        }
    }


}
