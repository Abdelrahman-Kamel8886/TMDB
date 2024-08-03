package com.abdo.tmdb.Ui.WatchList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.tmdb.local.MyRoomDatabase;
import com.abdo.tmdb.models.MovieDetailsModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListViewModel extends ViewModel {

    MutableLiveData<List<MovieDetailsModel>> LocalList = new MutableLiveData<>();
    MutableLiveData<Integer> error = new MutableLiveData<>();

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

}
