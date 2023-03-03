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
import com.abdo.tmdb.models.PersonModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerCastAdapter extends RecyclerView.Adapter<RecyclerCastAdapter.Holder> {


    private ArrayList<PersonModel.Cast> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(ArrayList<PersonModel.Cast> list) {
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
        if (list.get(position).getProfile_path()!=null){
            String url = Consts.IMAGE_URL+list.get(position).getProfile_path();
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.Image);
        }

        holder.Name.setText(list.get(position).getName());



    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView Image;
        TextView  Name;

        public Holder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.pesronImage);
            Name  = itemView.findViewById(R.id.personName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){
                        onItemClick.OnClick(list.get(getLayoutPosition()).getId());
                    }
                }
            });



        }
    }

   public interface OnItemClick{

        void OnClick(int CastId);

    }

}
