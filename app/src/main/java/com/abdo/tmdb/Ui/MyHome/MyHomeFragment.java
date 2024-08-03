package com.abdo.tmdb.Ui.MyHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.Category.CatsFragment;
import com.abdo.tmdb.Ui.Movie.MovieFragment;
import com.abdo.tmdb.Ui.WatchList.BackListener;
import com.abdo.tmdb.adapters.RecyclerMovieAdapter;
import com.abdo.tmdb.adapters.RecyclerNowPlayingAdapter;
import com.abdo.tmdb.databinding.FragmentMyHomeBinding;
import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.models.MovieModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;


public class MyHomeFragment extends Fragment implements BackListener {

    FragmentMyHomeBinding binding;
    MyHomeViewModel viewModel;
    RecyclerMovieAdapter adapter1 = new RecyclerMovieAdapter();
    RecyclerMovieAdapter adapter2 = new RecyclerMovieAdapter();
    RecyclerNowPlayingAdapter adapter3 = new RecyclerNowPlayingAdapter();
    RecyclerMovieAdapter adapter4 = new RecyclerMovieAdapter();

    public static BackListener listener;

    MeowBottomNavigation nav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyHomeBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyHomeViewModel.class);
        nav = getActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        if (SharedModel.getUpcomingFilms().size() ==0||SharedModel.getNowFilms().size()==0
                ||SharedModel.getPopularFilms().size()==0||SharedModel.getTopFilms().size()==0){
            getData();
        }
        else{
            goLocal();
        }



    }

    private void goLocal(){
        binding.bar.setVisibility(View.GONE);
        binding.mainLayout.setVisibility(View.VISIBLE);

        ArrayList<MovieModel.ResultsDTO> list1 = new ArrayList<>();
        ArrayList<MovieModel.ResultsDTO> list2 = new ArrayList<>();
        ArrayList<MovieModel.ResultsDTO> list3 = new ArrayList<>();
        ArrayList<MovieModel.ResultsDTO> list4 = new ArrayList<>();

        list1.addAll(SharedModel.getPopularFilms().subList(0,6));
        list2.addAll(SharedModel.getTopFilms().subList(0,6));
        list3.addAll(SharedModel.getNowFilms());
        list4.addAll(SharedModel.getUpcomingFilms().subList(0,6));

        adapter1.setList(list1);
        adapter2.setList(list2);
        adapter3.setList(list3);
        adapter4.setList(list4);

        binding.recycler1.setAdapter(adapter1);
        binding.recycler2.setAdapter(adapter2);
        binding.recycler3.setAdapter(adapter3);
        binding.recycler4.setAdapter(adapter4);

        adapter1.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
            @Override
            public void OnClick(int MovieId) {
                SharedModel.setId(MovieId);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie")
                        .addToBackStack("movie").commit();
            }
        });
        adapter2.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
            @Override
            public void OnClick(int MovieId) {
                SharedModel.setId(MovieId);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie")
                        .addToBackStack("movie").commit();
            }
        });
        adapter3.setOnItemClick(new RecyclerNowPlayingAdapter.OnItemClick() {
            @Override
            public void OnClick(int MovieId) {
                SharedModel.setId(MovieId);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, new MovieFragment(), "movie")
                        .addToBackStack("movie").commit();
            }
        });
        adapter4.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, new MovieFragment(), "movie")
                                .addToBackStack("movie").commit();
                    }
                });

        onClick();
    }

    private void getData(){
        viewModel.getPopularMovies();

        viewModel.Popular.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {

                ArrayList<MovieModel.ResultsDTO> list1 = new ArrayList<>();

                if (movieModel.getResults().size()>=6){
                    list1.addAll(movieModel.getResults().subList(0,6));
                    SharedModel.setPopularFilms((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());
                }
                else {
                    list1.addAll(movieModel.getResults());
                }

                adapter1.setList(list1);
                binding.recycler1.setAdapter(adapter1);

                adapter1.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() )
                                .addToBackStack("movie").commit();
                    }
                });
            }
        });

        viewModel.Top.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                ArrayList<MovieModel.ResultsDTO> list2 = new ArrayList<>();

                if (movieModel.getResults().size()>=6){
                    list2.addAll(movieModel.getResults().subList(0,6));
                    SharedModel.setTopFilms((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());
                }
                else {
                    list2.addAll(movieModel.getResults());
                }

                adapter2.setList(list2);
                binding.recycler2.setAdapter(adapter2);

                adapter2.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie")
                                .addToBackStack("movie").commit();
                    }
                });
            }
        });

        viewModel.Now.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                SharedModel.setNowFilms((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());
                adapter3.setList((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());
                binding.recycler3.setAdapter(adapter3);
                adapter3.setOnItemClick(new RecyclerNowPlayingAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie")
                                .addToBackStack("movie").commit();
                    }
                });
            }
        });

        viewModel.Upcoming.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                ArrayList<MovieModel.ResultsDTO> list4 = new ArrayList<>();
                SharedModel.setUpcomingFilms((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());

                if (movieModel.getResults().size()>=6){
                    list4.addAll(movieModel.getResults().subList(0,6));
                }
                else {
                    list4.addAll(movieModel.getResults());
                }

                adapter4.setList(list4);
                binding.recycler4.setAdapter(adapter4);

                adapter4.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie")
                                .addToBackStack("movie").commit();
                    }
                });

                binding.bar.setVisibility(View.GONE);
                binding.mainLayout.setVisibility(View.VISIBLE);
            }
        });

        onClick();




    }
    private void onClick(){
        binding.seeallpopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setSelectedCat(SharedModel.getPopularFilms());
                SharedModel.setSelectedTitle(getString(R.string.po));
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.home_frame , new CatsFragment() , "cat")
                        .addToBackStack("cat")
                        .commit();
            }
        });
        binding.seealltop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setSelectedCat(SharedModel.getTopFilms());
                SharedModel.setSelectedTitle(getString(R.string.ra));
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.home_frame , new CatsFragment() , "cat")
                        .addToBackStack("cat")
                        .commit();
            }
        });
        binding.seeallupcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setSelectedCat(SharedModel.getUpcomingFilms());
                SharedModel.setSelectedTitle(getString(R.string.up));
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.home_frame , new CatsFragment() , "cat")
                        .addToBackStack("cat")
                        .commit();
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
        Toast.makeText(getContext(), "Last back", Toast.LENGTH_SHORT).show();
    }
}