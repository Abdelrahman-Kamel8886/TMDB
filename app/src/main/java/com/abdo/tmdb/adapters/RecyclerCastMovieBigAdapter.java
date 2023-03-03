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
import com.abdo.tmdb.models.PersonMoviesModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerCastMovieBigAdapter extends RecyclerView.Adapter<RecyclerCastMovieBigAdapter.Holder> {


    private ArrayList<PersonMoviesModel.Cast> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(ArrayList<PersonMoviesModel.Cast> list) {
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

        if (list.get(position).getPoster_path() != null){
            String url = Consts.IMAGE_URL+list.get(position).getPoster_path();
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.MovieImage);
        }

        holder.MovieName.setText(list.get(position).getTitle());


    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieImage;
        TextView  MovieName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.image_movie);
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
