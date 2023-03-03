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
import com.abdo.tmdb.models.MovieModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerNowPlayingAdapter extends RecyclerView.Adapter<RecyclerNowPlayingAdapter.Holder> {


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
                .inflate(R.layout.item_movienow,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String url = Consts.IMAGE_URL+list.get(position).getPosterPath();
        String url2 = Consts.IMAGE_URL+list.get(position).getBackdropPath();

        holder.MovieName.setText(list.get(position).getTitle());
        holder.MovieDes.setText(list.get(position).getOverview());

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.MovieImage);

        Glide.with(holder.itemView.getContext())
                .load(url2)
                .into(holder.MovieBackground);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieBackground,MovieImage;
        TextView  MovieName , MovieDes;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.smallimg);
            MovieBackground  = itemView.findViewById(R.id.bigimg);
            MovieDes = itemView.findViewById(R.id.description);
            MovieName  = itemView.findViewById(R.id.movie_title);

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
