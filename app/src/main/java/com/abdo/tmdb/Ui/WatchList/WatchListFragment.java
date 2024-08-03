package com.abdo.tmdb.Ui.WatchList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.Movie.MovieFragment;
import com.abdo.tmdb.adapters.RecyclerLocalMovieAdapter;
import com.abdo.tmdb.databinding.FragmentWatchListBinding;
import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.models.MovieDetailsModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.List;


public class WatchListFragment extends Fragment implements BackListener {

    FragmentWatchListBinding binding;
    WatchListViewModel viewModel;
    RecyclerLocalMovieAdapter adapter = new RecyclerLocalMovieAdapter();
    MeowBottomNavigation nav;
    public static BackListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watch_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWatchListBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(WatchListViewModel.class);
        nav = getActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getData();
    }

    private void getData(){
        viewModel.getLocalMovies();
        viewModel.LocalList.observe(getViewLifecycleOwner(), new Observer<List<MovieDetailsModel>>() {
            @Override
            public void onChanged(List<MovieDetailsModel> movieDetailsModels) {
                if (movieDetailsModels.size()!=0){
                    binding.recycler.setVisibility(View.VISIBLE);
                    adapter.setList(movieDetailsModels);
                    binding.recycler.setAdapter(adapter);

                    adapter.setOnItemClick(new RecyclerLocalMovieAdapter.OnItemClick() {
                        @Override
                        public void OnClick(int MovieId) {
                            SharedModel.setId(MovieId);
                            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "m")
                                    .addToBackStack("m").commit();
                        }
                    });
                }
                else {
                    binding.empty.setVisibility(View.VISIBLE);
                }

            }
        });
        viewModel.error.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.empty.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        listener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        listener = this;
    }


    @Override
    public void onBackPressed() {
        nav.show(1,true);
    }

}