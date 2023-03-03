package com.abdo.tmdb.network;


import com.abdo.tmdb.Utils.Consts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConnection {




    private static Retrofit retrofit;
    private static Gson gson = new GsonBuilder().setLenient().create();

    private synchronized static Retrofit getRetrofit(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Consts.Token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Consts.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

        }
        return retrofit;
    }
    public static RetroServices getServices(){
        return getRetrofit().create(RetroServices.class);
    }
}