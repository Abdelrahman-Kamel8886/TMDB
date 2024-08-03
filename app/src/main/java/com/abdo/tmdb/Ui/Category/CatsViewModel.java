package com.abdo.tmdb.Ui.Category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.network.RetroConnection;

import java.util.Locale;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CatsViewModel extends ViewModel {

    MutableLiveData<MovieModel> Popular = new MutableLiveData<>();
    MutableLiveData<MovieModel> Top = new MutableLiveData<>();
    MutableLiveData<MovieModel> Upcoming = new MutableLiveData<>();


    public void getPopularMovies(){
        RetroConnection.getServices().getPopular(Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        Popular.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void getTopMovies(){
        RetroConnection.getServices().getTopRated(Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        Top.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void getUpcoming(){
        RetroConnection.getServices().getUpcoming(Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        Upcoming.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
