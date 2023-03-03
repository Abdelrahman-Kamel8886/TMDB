package com.abdo.tmdb.Ui.Movie;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.tmdb.Utils.SharedModel;
import com.abdo.tmdb.local.MyRoomDatabase;
import com.abdo.tmdb.models.MovieDetailsModel;
import com.abdo.tmdb.models.MovieImagesModel;
import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.models.PersonModel;
import com.abdo.tmdb.models.VideoModel;
import com.abdo.tmdb.network.RetroConnection;

import java.util.List;
import java.util.Locale;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    MutableLiveData<MovieDetailsModel> MovieDetails = new MutableLiveData<>();
    MutableLiveData<MovieModel> SimilarMovies =new MutableLiveData<>();
    MutableLiveData<PersonModel> Credits = new MutableLiveData<>();
    MutableLiveData<MovieImagesModel> Images = new MutableLiveData<>();
    MutableLiveData<VideoModel> Videos = new MutableLiveData<>();
    MutableLiveData<List<MovieDetailsModel>> LocalList = new MutableLiveData<>();
    MutableLiveData<Integer> error = new MutableLiveData<>();

    MutableLiveData<Integer> Cached = new MutableLiveData<>();
    MutableLiveData<Integer> Removed = new MutableLiveData<>();

    public void getMovieDetails(){
        RetroConnection.getServices().getMovieDetails(SharedModel.getId() , Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieDetailsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieDetailsModel movieDetailsModel) {
                        MovieDetails.setValue(movieDetailsModel);
                        getImages();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //getMovieDetails();
                        Log.e("TAG", "onError: details"+SharedModel.getId());

                    }
                });
    }
    private void getImages(){
        RetroConnection.getServices().getImages(SharedModel.getId(), Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieImagesModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieImagesModel movieImagesModel) {
                        Images.setValue(movieImagesModel);
                        getVideos();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: images" );

                    }
                });
    }
    private void getVideos(){
        RetroConnection.getServices().getVideos(SharedModel.getId(), Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<VideoModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(VideoModel videoModel) {
                        Videos.setValue(videoModel);
                        getCast();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    private void getCast(){
        RetroConnection.getServices().getCredits(SharedModel.getId(), Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PersonModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(PersonModel personModel) {
                        Credits.setValue(personModel);
                        getSimilar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: cast" );

                    }
                });
    }
    private void getSimilar(){
        RetroConnection.getServices().getSimilar(SharedModel.getId(), Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        SimilarMovies.setValue(movieModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: sim" );

                    }
                });
    }

    public void getLocalMovies(){
        MyRoomDatabase.getInstance().getDao().getall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MovieDetailsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<MovieDetailsModel> movieDetailsModels) {
                        LocalList.setValue(movieDetailsModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(1);

                    }
                });
    }


    public  void cache(List<MovieDetailsModel> list){

        MyRoomDatabase.getInstance().getDao().insert(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Cached.setValue(1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public  void delete(MovieDetailsModel model){

        MyRoomDatabase.getInstance().getDao().delete(model).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Removed.setValue(1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

}
