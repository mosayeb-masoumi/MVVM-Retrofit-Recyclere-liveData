package com.example.testmvvm2.main_activity;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.example.testmvvm2.network.Service;
import com.example.testmvvm2.network.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    MutableLiveData<List<Movie>> mutableMovies = new MutableLiveData<>();
    Context context;


    public Repository(Context context) {
        this.context = context;

    }

    MutableLiveData<List<Movie>> getMutableMovies(){

        Service service = new ServiceProvider(context).getmService();
        Call<List<Movie>> call = service.getMovie();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.code()==200){
                    mutableMovies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

        return mutableMovies;
    }




}
