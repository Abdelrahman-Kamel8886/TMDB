package com.abdo.tmdb.Ui.Search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.network.RetroConnection;

import java.util.Locale;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    MutableLiveData<MovieModel> Movies = new MutableLiveData<>();

    public void Search(String query){
        RetroConnection.getServices().Search(query, Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        Movies.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
