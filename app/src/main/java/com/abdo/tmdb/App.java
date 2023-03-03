package com.abdo.tmdb;

import android.app.Application;

import com.abdo.tmdb.local.MyRoomDatabase;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDatabase.initRoom(this);
    }
}
