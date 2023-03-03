package com.abdo.tmdb.local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.abdo.tmdb.models.CacheModel;
import com.abdo.tmdb.models.MovieDetailsModel;
import com.abdo.tmdb.models.MovieModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao

public interface MyDao {


    @Insert
    Completable insert (List<MovieDetailsModel> list);

    @Query("select * from MovieDetailsModel")

    Single <List<MovieDetailsModel>> getall();


    @Delete
    Completable delete (MovieDetailsModel d);

}
