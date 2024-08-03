package com.abdo.tmdb.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.abdo.tmdb.R;
import com.abdo.tmdb.Ui.Home.HomeFragment;
import com.abdo.tmdb.Ui.MyHome.MyHomeFragment;
import com.abdo.tmdb.Ui.Search.SearchFragment;
import com.abdo.tmdb.Ui.WatchList.WatchListFragment;
import com.abdo.tmdb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(binding.frame.getId() , new HomeFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onBackPressed() {

        if (WatchListFragment.listener != null){
            WatchListFragment.listener.onBackPressed();
        }
        else if (SearchFragment.listener != null){
            SearchFragment.listener.onBackPressed();
        }
        else if (MyHomeFragment.listener != null){
            new AlertDialog.Builder(this)
                    .setMessage(R.string.exit)
                    .setCancelable(true)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
        else{
            super.onBackPressed();

        }
    }
}