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
import com.abdo.tmdb.models.MovieDetailsModel;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerLocalMovieAdapter extends RecyclerView.Adapter<RecyclerLocalMovieAdapter.Holder> {


    private List<MovieDetailsModel> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(List<MovieDetailsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_localmovie,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if (list.get(position).getPoster_path()!=null){
            String url = Consts.IMAGE_URL+list.get(position).getPoster_path();
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.MovieImage);
        }



        try {
            holder.MovieName.setText(list.get(position).getTitle()+"");
            holder.MovieRate.setText(""+new DecimalFormat("##.#").format(list.get(position).getVote_average()));
            holder.MovieLang.setText(list.get(position).getOriginal_language()+" ");
            holder.MovieDate.setText(list.get(position).getRelease_date().substring(0,4)+" ");

        }catch (Exception e){

        }

        if (position == list.size()-1){
            holder.layout.setVisibility(View.VISIBLE);
        }
        else {
            holder.layout.setVisibility(View.GONE);
        }






    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieImage;
        TextView  MovieName , MovieRate , MovieDate , MovieLang;
        ConstraintLayout layout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.local_img);
            MovieRate  = itemView.findViewById(R.id.rate);
            MovieDate = itemView.findViewById(R.id.date);
            MovieLang  = itemView.findViewById(R.id.language);
            MovieName = itemView.findViewById(R.id.movie_title);
            layout = itemView.findViewById(R.id.mainLayout);

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
