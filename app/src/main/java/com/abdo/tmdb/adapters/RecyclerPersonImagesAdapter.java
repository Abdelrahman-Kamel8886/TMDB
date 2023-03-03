package com.abdo.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Utils.Consts;
import com.abdo.tmdb.models.PersonImagesModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerPersonImagesAdapter extends RecyclerView.Adapter<RecyclerPersonImagesAdapter.Holder> {


    private ArrayList<PersonImagesModel.Profiles> list;
    public void setList(ArrayList<PersonImagesModel.Profiles> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String url = Consts.IMAGE_URL+list.get(position).getFile_path();

        holder.Name.setVisibility(View.GONE);

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.Image);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView Image;
        TextView  Name;

        public Holder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.pesronImage);
            Name  = itemView.findViewById(R.id.personName);

        }
    }


}
