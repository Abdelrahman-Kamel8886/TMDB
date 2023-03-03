package com.abdo.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.tmdb.R;
import com.abdo.tmdb.models.VideoModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerTrailersAdapter extends RecyclerView.Adapter<RecyclerTrailersAdapter.Holder> {


    private List<VideoModel.Results> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(List<VideoModel.Results> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {



        if (list.get(position).getKey()!=null){
            String thumbnailul = "https://img.youtube.com/vi/" + list.get(position).getKey() + "/hqdefault.jpg";
            Glide.with(holder.itemView.getContext())
                    .load(thumbnailul)
                    .into(holder.MovieImage);
        }






    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.Trailer_Image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){
                        onItemClick.OnClick(list.get(getLayoutPosition()).getKey());
                    }
                }
            });



        }
    }

   public interface OnItemClick{

        void OnClick(String VideoKey);

    }

}
