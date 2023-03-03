package com.abdo.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Utils.Consts;
import com.abdo.tmdb.models.MovieModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerCatsAdapter extends RecyclerView.Adapter<RecyclerCatsAdapter.Holder> {


    private ArrayList<MovieModel.ResultsDTO> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(ArrayList<MovieModel.ResultsDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_moviebig,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if(list.get(position).getPosterPath()!=null){
            String url = Consts.IMAGE_URL+list.get(position).getPosterPath();
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.MovieImage);
        }

        holder.MovieName.setText(list.get(position).getTitle());

        if (position == list.size()-2){
            holder.mainlayout.setVisibility(View.VISIBLE);
        }
        else if (position == list.size()-1){
            holder.mainlayout.setVisibility(View.VISIBLE);
        }
        else {
            holder.mainlayout.setVisibility(View.GONE);
        }


    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieImage;
        TextView  MovieName;
        ConstraintLayout mainlayout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.image_movie);
            MovieName  = itemView.findViewById(R.id.movie_title);
            mainlayout = itemView.findViewById(R.id.mainLayout);

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

        void OnClick(int MovieId);

    }

}
