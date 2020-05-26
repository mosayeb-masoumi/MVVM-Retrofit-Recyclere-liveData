package com.example.testmvvm2.network;

import com.example.testmvvm2.main_activity.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Service {

    @GET("volley_array.json")
    Call<List<Movie>> getMovie();
}
