package com.abdo.tmdb.Ui.Person;

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
import com.abdo.tmdb.Ui.Movie.MovieFragment;
import com.abdo.tmdb.Ui.Person.Cast.CastFragment;
import com.abdo.tmdb.Ui.Person.Crew.CrewFragment;
import com.abdo.tmdb.adapters.RecyclerPersonImagesAdapter;
import com.abdo.tmdb.adapters.RecyclerCrewMovieAdapter;
import com.abdo.tmdb.adapters.RecyclerCastMovieAdapter;
import com.abdo.tmdb.adapters.RecyclerTextAdapter;
import com.abdo.tmdb.databinding.FragmentPersonBinding;
import com.abdo.tmdb.Utils.Consts;
import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.models.PersonDetailsModel;
import com.abdo.tmdb.models.PersonImagesModel;
import com.abdo.tmdb.models.PersonMoviesModel;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class PersonFragment extends Fragment {

    FragmentPersonBinding binding;
    PersonViewModel viewModel;
    ArrayList<SlideModel> ImagesList = new ArrayList<>();
    RecyclerPersonImagesAdapter imagesAdapter = new RecyclerPersonImagesAdapter();
    RecyclerTextAdapter textAdapter = new RecyclerTextAdapter();

    RecyclerCastMovieAdapter adapter1 = new RecyclerCastMovieAdapter();
    RecyclerCrewMovieAdapter adapter2 = new RecyclerCrewMovieAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPersonBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        viewModel.getDetails();
        viewModel.Details.observe(getViewLifecycleOwner(), new Observer<PersonDetailsModel>() {
            @Override
            public void onChanged(PersonDetailsModel personDetailsModel) {

                try {
                    binding.name.setText(personDetailsModel.getName());
                    SharedModel.setSelectedactorname(personDetailsModel.getName());
                    binding.rule.setText(personDetailsModel.getKnown_for_department());
                    if (personDetailsModel.getBiography().equals("")){
                        binding.biography.setVisibility(View.GONE);
                        binding.txt1.setVisibility(View.GONE);
                    }
                    else{
                        binding.biography.setText(personDetailsModel.getBiography());
                    }
                    binding.imdb.setText(personDetailsModel.getImdb_id());
                    binding.place.setText(personDetailsModel.getPlace_of_birth());


                    String country = personDetailsModel.getPlace_of_birth();
                    country = country.split(",")[country.split(",").length-1];
                    binding.Country.setText(country);


                    binding.Popularity.setText(""+personDetailsModel.getPopularity());
                }catch (Exception e){

                }

                if (personDetailsModel.getDeathday() != null){
                    binding.date.setText(personDetailsModel.getBirthday()+" - "+personDetailsModel.getDeathday());
                }
                else {
                    binding.date.setText(personDetailsModel.getBirthday());
                }

                if (personDetailsModel.getProfile_path() != null){
                    Glide.with(getContext())
                            .load(Consts.IMAGE_URL +personDetailsModel.getProfile_path())
                            .into(binding.profile);
                }



                textAdapter.setList((ArrayList<String>) personDetailsModel.getAlso_known_as());
                binding.recyclerknown.setAdapter(textAdapter);

            }
        });
        viewModel.Images.observe(getViewLifecycleOwner(), new Observer<PersonImagesModel>() {
            @Override
            public void onChanged(PersonImagesModel personImagesModel) {
                for (PersonImagesModel.Profiles p : personImagesModel.getProfiles()) {
                    ImagesList.add(new SlideModel(Consts.IMAGE_URL+p.getFile_path(), ScaleTypes.FIT));
                }
                binding.imageSlider.setImageList(ImagesList);

                if (ImagesList.isEmpty()){
                    binding.recyclerimages.setVisibility(View.GONE);
                    binding.txt2.setVisibility(View.GONE);
                }
                else{
                    imagesAdapter.setList((ArrayList<PersonImagesModel.Profiles>) personImagesModel.getProfiles());
                    binding.recyclerimages.setAdapter(imagesAdapter);
                }


            }
        });
        viewModel.Movies.observe(getViewLifecycleOwner(), new Observer<PersonMoviesModel>() {
            @Override
            public void onChanged(PersonMoviesModel personMoviesModel) {

                if (personMoviesModel.getCast().size()!=0){

                    ArrayList<PersonMoviesModel.Cast> list1 = new ArrayList<>();
                    if (personMoviesModel.getCast().size()>=5){
                        list1.addAll(personMoviesModel.getCast().subList(0,5));
                        adapter1.setList(list1);
                    }
                    else {
                        adapter1.setList((ArrayList<PersonMoviesModel.Cast>) personMoviesModel.getCast());
                    }
                    binding.recyclermovies1.setAdapter(adapter1);
                    adapter1.setOnItemClick(new RecyclerCastMovieAdapter.OnItemClick() {
                        @Override
                        public void OnClick(int MovieId) {
                            SharedModel.setId(MovieId);
                            requireActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.home_frame , new MovieFragment() , "movie").commit();
                        }
                    });
                }
                else {
                    binding.txt3.setVisibility(View.GONE);
                    binding.seeallcast.setVisibility(View.GONE);
                    binding.recyclermovies1.setVisibility(View.GONE);
                }

                if (personMoviesModel.getCrew().size()!=0){
                    ArrayList<PersonMoviesModel.Crew> list2 = new ArrayList<>();
                    if (personMoviesModel.getCrew().size()>=5){
                        list2.addAll(personMoviesModel.getCrew().subList(0,5));
                        adapter2.setList(list2);
                    }
                    else {
                        adapter2.setList((ArrayList<PersonMoviesModel.Crew>) personMoviesModel.getCrew());
                    }

                    binding.recyclermovies2.setAdapter(adapter2);
                    adapter2.setOnItemClick(new RecyclerCrewMovieAdapter.OnItemClick() {
                        @Override
                        public void OnClick(int MovieId) {
                            SharedModel.setId(MovieId);
                            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MovieFragment()).commit();
                        }
                    });
                }
                else {
                    binding.txt4.setVisibility(View.GONE);
                    binding.seeallcrew.setVisibility(View.GONE);
                    binding.recyclermovies2.setVisibility(View.GONE);
                }

                binding.bar.setVisibility(View.GONE);
                binding.mainLayout.setVisibility(View.VISIBLE);

                binding.seeallcast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedModel.setSelectedPersonMoviesModel(personMoviesModel);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CastFragment()).commit();

                    }
                });
                binding.seeallcrew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedModel.setSelectedPersonMoviesModel(personMoviesModel);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CrewFragment()).commit();
                    }
                });

            }
        });


    }
}