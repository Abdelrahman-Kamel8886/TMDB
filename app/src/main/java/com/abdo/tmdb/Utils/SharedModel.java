package com.abdo.tmdb.Utils;

import com.abdo.tmdb.models.MovieModel;
import com.abdo.tmdb.models.PersonMoviesModel;

import java.util.ArrayList;

public class SharedModel  {

    public static int id =0;
    public static int castId =0;
    public static String SelectedTitle ="";

    public static String Selectedactorname ="";

    public static String getSelectedactorname() {
        return Selectedactorname;
    }

    public static void setSelectedactorname(String selectedactorname) {
        Selectedactorname = selectedactorname;
    }

    public static PersonMoviesModel SelectedPersonMoviesModel;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SharedModel.id = id;
    }

    public static int getCastId() {
        return castId;
    }

    public static void setCastId(int castId) {
        SharedModel.castId = castId;
    }

    public static int cat =1;

    public static int getCat() {
        return cat;
    }

    public static void setCat(int cat) {
        SharedModel.cat = cat;
    }

    public static ArrayList<MovieModel.ResultsDTO> PopularFilms = new ArrayList<>() ;
    public static ArrayList<MovieModel.ResultsDTO> TopFilms = new ArrayList<>();
    public static ArrayList<MovieModel.ResultsDTO> NowFilms = new ArrayList<>() ;
    public static ArrayList<MovieModel.ResultsDTO> UpcomingFilms = new ArrayList<>() ;

    public static ArrayList<MovieModel.ResultsDTO> SelectedCat = new ArrayList<>() ;

    public static ArrayList<MovieModel.ResultsDTO> getPopularFilms() {
        return PopularFilms;
    }

    public static void setPopularFilms(ArrayList<MovieModel.ResultsDTO> popularFilms) {
        PopularFilms = popularFilms;
    }

    public static ArrayList<MovieModel.ResultsDTO> getTopFilms() {
        return TopFilms;
    }

    public static void setTopFilms(ArrayList<MovieModel.ResultsDTO> topFilms) {
        TopFilms = topFilms;
    }

    public static ArrayList<MovieModel.ResultsDTO> getNowFilms() {
        return NowFilms;
    }

    public static void setNowFilms(ArrayList<MovieModel.ResultsDTO> nowFilms) {
        NowFilms = nowFilms;
    }

    public static ArrayList<MovieModel.ResultsDTO> getUpcomingFilms() {
        return UpcomingFilms;
    }

    public static void setUpcomingFilms(ArrayList<MovieModel.ResultsDTO> upcomingFilms) {
        UpcomingFilms = upcomingFilms;
    }

    public static String getSelectedTitle() {
        return SelectedTitle;
    }

    public static void setSelectedTitle(String selectedTitle) {
        SelectedTitle = selectedTitle;
    }

    public static ArrayList<MovieModel.ResultsDTO> getSelectedCat() {
        return SelectedCat;
    }

    public static void setSelectedCat(ArrayList<MovieModel.ResultsDTO> selectedCat) {
        SelectedCat = selectedCat;
    }

    public static PersonMoviesModel getSelectedPersonMoviesModel() {
        return SelectedPersonMoviesModel;
    }

    public static void setSelectedPersonMoviesModel(PersonMoviesModel selectedPersonMoviesModel) {
        SelectedPersonMoviesModel = selectedPersonMoviesModel;
    }
}
