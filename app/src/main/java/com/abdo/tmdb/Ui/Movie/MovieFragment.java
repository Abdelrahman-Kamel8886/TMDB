package com.abdo.tmdb.Ui.Movie;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.Person.PersonFragment;
import com.abdo.tmdb.adapters.RecyclerCastAdapter;
import com.abdo.tmdb.adapters.RecyclerCrewAdapter;
import com.abdo.tmdb.adapters.RecyclerMovieAdapter;
import com.abdo.tmdb.adapters.RecyclerMovieImagesAdapter;
import com.abdo.tmdb.adapters.RecyclerTrailersAdapter;
import com.abdo.tmdb.databinding.FragmentMovieBinding;
import com.abdo.tmdb.Utils.Consts;
import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.models.MovieDetailsModel;
import com.abdo.tmdb.models.MovieImagesModel;
import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.models.PersonModel;
import com.abdo.tmdb.models.VideoModel;
import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MovieFragment extends Fragment {

    FragmentMovieBinding binding;
    MeowBottomNavigation nav;
    MovieViewModel viewModel;
    RecyclerMovieAdapter adapter = new RecyclerMovieAdapter();
    RecyclerCastAdapter catsAdapter = new RecyclerCastAdapter();
    RecyclerCrewAdapter crewAdapter = new RecyclerCrewAdapter();
    RecyclerMovieImagesAdapter imagesAdapter = new RecyclerMovieImagesAdapter();
    RecyclerTrailersAdapter trailersAdapter = new RecyclerTrailersAdapter();
    ArrayList<MovieDetailsModel> list = new ArrayList<>();
    boolean checked = false;
    MovieDetailsModel detailsModel = new MovieDetailsModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMovieBinding.bind(view);
        nav = getActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        getData();

    }

    private void getData(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        viewModel.getMovieDetails();
        viewModel.MovieDetails.observe(getViewLifecycleOwner(), new Observer<MovieDetailsModel>() {
            @Override
            public void onChanged(MovieDetailsModel movieDetailsModel) {
                list.add(movieDetailsModel);
                detailsModel = movieDetailsModel;
                try {
                    binding.title.setText(movieDetailsModel.getTitle());
                    binding.overview.setText(movieDetailsModel.getOverview());
                    binding.orginal.setText(movieDetailsModel.getOriginal_title());
                    binding.language.setText(movieDetailsModel.getOriginal_language());
                    if (movieDetailsModel.getOverview().isEmpty()){
                        binding.overview.setVisibility(View.GONE);
                        binding.txt1.setVisibility(View.GONE);
                    }
                    if (movieDetailsModel.getTagline().isEmpty()){
                        binding.tagline.setVisibility(View.GONE);
                    }
                    else {
                        binding.tagline.setText(movieDetailsModel.getTagline());
                    }
                    String rate = Double.toString(movieDetailsModel.getVote_average());
                    if (rate.endsWith("0")){
                        binding.rate.setText(rate);
                    }
                    else if (rate.length()==1){
                        binding.rate.setText(rate+".0");
                    }
                    else {
                        binding.rate.setText(""+new DecimalFormat("##.#").format(movieDetailsModel.getVote_average()));
                    }
                    binding.status.setText(movieDetailsModel.getStatus());
                    binding.votes.setText(movieDetailsModel.getVote_count()+"");
                    double amount =(double) movieDetailsModel.getRevenue();
                    binding.revenue.setText("$"+String.format("%,.2f", amount));

                    String date = movieDetailsModel.getRelease_date().substring(0,4);
                    String countries = "";

                    for(MovieDetailsModel.ProductionCountries c : movieDetailsModel.getProduction_countries()){
                        countries+=" , "+c.getIso_3166_1();
                    }
                    String genres = "";

                    for (MovieDetailsModel.Genres g :movieDetailsModel.getGenres()) {
                        genres += " , "+g.getName();
                    }

                    binding.information.setText(genres.substring(3));
                    binding.date.setText(date+countries);
                }catch (Exception e){

                }

                if (movieDetailsModel.getPoster_path()!=null){
                    Glide.with(getContext())
                            .load(Consts.IMAGE_URL+movieDetailsModel.getPoster_path())
                            .into(binding.smallimg);
                }
                if (movieDetailsModel.getBackdrop_path()!=null){
                    Glide.with(getContext())
                            .load(Consts.IMAGE_URL+movieDetailsModel.getBackdrop_path())
                            .into(binding.bigimg);
                }

                if(movieDetailsModel.getVote_average()<4){
                    binding.card1.setStrokeColor(Color.RED);
                }
                else if(movieDetailsModel.getVote_average()>=4 && movieDetailsModel.getVote_average()<6){
                    binding.card1.setStrokeColor(Color.YELLOW);
                }
                else if(movieDetailsModel.getVote_average()>=6 && movieDetailsModel.getVote_average()<8){
                    binding.card1.setStrokeColor(Color.CYAN);
                }
                else {
                    binding.card1.setStrokeColor(Color.GREEN);
                }







            }
        });
        viewModel.Credits.observe(getViewLifecycleOwner(), new Observer<PersonModel>() {
            @Override
            public void onChanged(PersonModel personModel) {
                catsAdapter.setList((ArrayList<PersonModel.Cast>) personModel.getCast());
                binding.recyclerCast.setAdapter(catsAdapter);

                crewAdapter.setList((ArrayList<PersonModel.Crew>) personModel.getCrew());
                binding.recyclerCrew.setAdapter(crewAdapter);

                if (personModel.getCast().size()==0){
                    binding.txt3.setVisibility(View.GONE);
                    binding.recyclerCast.setVisibility(View.GONE);
                }
                if (personModel.getCrew().size()==0){
                    binding.txt5.setVisibility(View.GONE);
                    binding.recyclerCrew.setVisibility(View.GONE);
                }

                catsAdapter.setOnItemClick(new RecyclerCastAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int CastId) {
                        SharedModel.setCastId(CastId);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_frame , new PersonFragment() , "cast").commit();
                    }
                });
                crewAdapter.setOnItemClick(new RecyclerCrewAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int CrewId) {
                        SharedModel.setCastId(CrewId);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_frame , new PersonFragment() , "crew").commit();
                    }
                });
            }
        });
        viewModel.Videos.observe(getViewLifecycleOwner(), new Observer<VideoModel>() {
            @Override
            public void onChanged(VideoModel videoModel) {
                if (videoModel.getResults().size()!=0){
                    trailersAdapter.setList(videoModel.getResults());
                    binding.recyclerTrailers.setAdapter(trailersAdapter);

                    trailersAdapter.setOnItemClick(new RecyclerTrailersAdapter.OnItemClick() {
                        @Override
                        public void OnClick(String VideoKey) {
                            String url = "https://www.youtube.com/watch?v="+VideoKey;
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        }
                    });
                }
                else {
                    binding.txt2.setVisibility(View.GONE);
                    binding.recyclerTrailers.setVisibility(View.GONE);
                }
            }
        });
        viewModel.SimilarMovies.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                if (movieModel.getResults().size()==0){
                    binding.txt4.setVisibility(View.GONE);
                    binding.recyclersimilar.setVisibility(View.GONE);
                }
                adapter.setList((ArrayList<MovieModel.ResultsDTO>) movieModel.getResults());
                binding.recyclersimilar.setAdapter(adapter);

                adapter.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
                    @Override
                    public void OnClick(int MovieId) {
                        SharedModel.setId(MovieId);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment() , "movie").commit();
                    }
                });
            }
        });
        viewModel.Images.observe(getViewLifecycleOwner(), new Observer<MovieImagesModel>() {
            @Override
            public void onChanged(MovieImagesModel movieImagesModel) {

                if (movieImagesModel.getBackdrops().size()!=0){
                    imagesAdapter.setList((ArrayList<MovieImagesModel.Backdrops>) movieImagesModel.getBackdrops());
                    binding.recyclerImages.setAdapter(imagesAdapter);
                }
                else {
                    binding.recyclerImages.setVisibility(View.GONE);
                    binding.imgtxt.setVisibility(View.GONE);
                }
            }
        });


        CheckWatchList();




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void CheckWatchList(){
        viewModel.getLocalMovies();
        viewModel.LocalList.observe(getViewLifecycleOwner(), new Observer<List<MovieDetailsModel>>() {
            @Override
            public void onChanged(List<MovieDetailsModel> movieDetailsModels) {
                for (MovieDetailsModel model : movieDetailsModels) {
                    if (model.getId()==SharedModel.getId()){
                        checked = true;
                        break;
                    }
                }
                if (checked){
                    binding.book.setBackground(getResources().getDrawable(R.drawable.bookadded));
                }
                else{
                    binding.book.setBackground(getResources().getDrawable(R.drawable.unbooked));
                }
                binding.bar.setVisibility(View.GONE);
                binding.book.setVisibility(View.VISIBLE);
                binding.mainLayout.setVisibility(View.VISIBLE);
                onClicks();
            }
        });



    }
    private void onClicks(){
        binding.book.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                if (checked){
                    Remove();
                }
                else {
                    Cache();
                }
            }
        });

    }

    private void Cache(){
        viewModel.cache(list);
        viewModel.Cached.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                checked = true ;
                binding.book.setBackground(getResources().getDrawable(R.drawable.bookadded));
                Toast.makeText(getContext(), R.string.addWatch , Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void Remove(){
        viewModel.delete(detailsModel);
        viewModel.Removed.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                checked = false;
                binding.book.setBackground(getResources().getDrawable(R.drawable.unbooked));
                Toast.makeText(getContext(), R.string.removedWatch, Toast.LENGTH_SHORT).show();
            }
        });

    }
}