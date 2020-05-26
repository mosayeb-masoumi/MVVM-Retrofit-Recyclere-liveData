package com.example.testmvvm2.main_activity;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvvm2.BR;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends BaseObservable {

    MutableLiveData<List<MyViewModel>> mutableMovies = new MutableLiveData<>();
    private List<MyViewModel> moviesList =  new ArrayList<>();

    Context context;


    public String id;
    public String image;
    public String title;

    public MyViewModel(Movie movie) {
        this.id = movie.id;
        this.image = movie.image;
        this.title = movie.title;
    }

    public MyViewModel(Context context) {
        this.context = context;


//        for (int i = 0; i < 5; i++) {
//            Movie movie = new Movie("id" + i, "image" + i,"title"+i);
//            MyViewModel myViewModel = new MyViewModel(movie);
//            moviesList.add(myViewModel);
//        }
//
//        mutableMovies.setValue(moviesList);



//         connect to server
        Repository repository = new Repository(context);
        repository.getMutableMovies().observe((LifecycleOwner) context, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                for (int i = 0; i <movies.size() ; i++) {

                    MyViewModel myViewModel = new MyViewModel(movies.get(i));
                    moviesList.add(myViewModel);
                }



                mutableMovies.setValue(moviesList);
               notifyPropertyChanged(BR.moviesList);
            }
        });


    }

    @BindingAdapter("bind:recyclerMovie")
    public static void recyclerViewBinder(final RecyclerView recyclerView,  MutableLiveData<List<MyViewModel>> mutableMovies) {

        mutableMovies.observe((LifecycleOwner) recyclerView.getContext(), new Observer<List<MyViewModel>>() {
            @Override
            public void onChanged(List<MyViewModel> myViewModels) {

                MovieAdapter userAdapter =new MovieAdapter(myViewModels ,recyclerView.getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(userAdapter);
            }
        });
    }



    public void onSubmitClick() {

    }



    ///----------------------------*****----------------------------------
    // getter setter for movie items

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }



    @Bindable
    public MutableLiveData<List<MyViewModel>> getMutableMovies() {
        return mutableMovies;
    }


    ///----------------------------*****----------------------------------

    @Bindable
    public List<MyViewModel> getMoviesList() {
        return moviesList;
    }

//    public void setMoviesList(List<MyViewModel> moviesList) {
//        this.moviesList = moviesList;
//        notifyPropertyChanged(BR.moviesList);
//    }


}
