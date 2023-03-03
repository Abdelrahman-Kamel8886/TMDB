package com.abdo.tmdb.network;



import com.abdo.tmdb.models.MovieDetailsModel;
import com.abdo.tmdb.models.MovieImagesModel;
import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.models.PersonDetailsModel;
import com.abdo.tmdb.models.PersonImagesModel;
import com.abdo.tmdb.models.PersonModel;
import com.abdo.tmdb.models.PersonMoviesModel;
import com.abdo.tmdb.models.VideoModel;

import java.util.SplittableRandom;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroServices {


    @GET("discover/movie")
    Single<MovieModel> getAllMovies(@Query("language") String lang);

    @GET("movie/popular")
    Single<MovieModel> getPopular(@Query("language") String lang);

    @GET("movie/top_rated")
    Single<MovieModel> getTopRated(@Query("language") String lang);

    @GET("movie/upcoming")
    Single<MovieModel> getUpcoming(@Query("language") String lang);

    @GET("movie/now_playing")
    Single<MovieModel> getnow(@Query("language") String lang);

    @GET("movie/{movie_id}")
    Single<MovieDetailsModel> getMovieDetails(@Path("movie_id") int MovieId , @Query("language") String lang);

    @GET("movie/{movie_id}/similar")
    Single<MovieModel> getSimilar(@Path("movie_id") int MovieId , @Query("language") String lang);

    @GET("movie/{movie_id}/credits")
    Single<PersonModel> getCredits(@Path("movie_id") int MovieId , @Query("language") String lang);

    @GET("movie/{movie_id}/images")
    Single<MovieImagesModel> getImages(@Path("movie_id") int MovieId ,@Query("language") String lang);

    @GET("movie/{movie_id}/videos")
    Single<VideoModel> getVideos(@Path("movie_id") int MovieId,@Query("language") String lang);

    @GET("person/{person_id}")
    Single<PersonDetailsModel> getPersonDetails(@Path("person_id") int PersonId,@Query("language") String lang);

    @GET("person/{person_id}/images")
    Single<PersonImagesModel> getPersonImages(@Path("person_id") int PersonId,@Query("language") String lang);

    @GET("person/{person_id}/movie_credits")
    Single<PersonMoviesModel> getPersonMovies(@Path("person_id") int PersonId,@Query("language") String lang);

    @GET("search/movie")
    Single<MovieModel> Search(@Query("query")String query,@Query("language") String lang);





}
