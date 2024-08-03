package com.abdo.tmdb.Ui.MyHome;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.network.RetroConnection;

import java.util.Locale;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyHomeViewModel extends ViewModel {

    MutableLiveData<MovieModel> Popular = new MutableLiveData<>();
    MutableLiveData<MovieModel> Top = new MutableLiveData<>();
    MutableLiveData<MovieModel> Upcoming = new MutableLiveData<>();
    MutableLiveData<MovieModel> Now = new MutableLiveData<>();


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
                        getTopMovies();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getPopularMovies();
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
                        getUpcoming();
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
                        getNow();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void getNow(){
        RetroConnection.getServices().getnow(Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        Now.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
