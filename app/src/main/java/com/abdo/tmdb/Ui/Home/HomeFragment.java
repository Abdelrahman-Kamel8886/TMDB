package com.abdo.tmdb.Ui.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.MyHome.MyHomeFragment;
import com.abdo.tmdb.Ui.Search.SearchFragment;
import com.abdo.tmdb.Ui.WatchList.WatchListFragment;
import com.abdo.tmdb.databinding.FragmentHomeBinding;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);
        binding.nav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_search_24));
        binding.nav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        binding.nav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_bookmark_border_24));

        binding.nav.show(1,true);

        binding.nav.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MyHomeFragment()).commit();
                        break;
                    case 2:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new SearchFragment()).commit();
                        break;
                    case 3:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new WatchListFragment()).commit();
                        break;
                }
                return null;
            }
        });

        binding.nav.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MyHomeFragment()).commit();
                        break;
                    case 2:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new SearchFragment()).commit();
                        break;
                    case 3:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new WatchListFragment()).commit();
                        break;
                }
                return null;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}