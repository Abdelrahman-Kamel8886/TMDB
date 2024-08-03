package com.abdo.tmdb.Ui.Person.Cast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.Movie.MovieFragment;
import com.abdo.tmdb.adapters.RecyclerCastMovieBigAdapter;
import com.abdo.tmdb.databinding.FragmentCastBinding;
import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.models.PersonMoviesModel;

import java.util.ArrayList;


public class CastFragment extends Fragment {

    FragmentCastBinding binding;
    RecyclerCastMovieBigAdapter adapter = new RecyclerCastMovieBigAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCastBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        adapter.setList((ArrayList<PersonMoviesModel.Cast>) SharedModel.getSelectedPersonMoviesModel().getCast());
        binding.recycler.setAdapter(adapter);
        binding.title.setText(SharedModel.getSelectedactorname());

        adapter.setOnItemClick(new RecyclerCastMovieBigAdapter.OnItemClick() {
            @Override
            public void OnClick(int MovieId) {
                SharedModel.setId(MovieId);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_frame , new MovieFragment() , "movie").commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}