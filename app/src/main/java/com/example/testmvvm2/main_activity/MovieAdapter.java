package com.example.testmvvm2.main_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvvm2.R;
import com.example.testmvvm2.databinding.ItemMovieBinding;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MyViewModel> list = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public MovieAdapter(List<MyViewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null)
            layoutInflater=LayoutInflater.from(parent.getContext());

        ItemMovieBinding movieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item,parent,false);

        return new ViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyViewModel model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding itemMovieBinding;
        public ViewHolder(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }

        private void bind(MyViewModel myViewModel){
            this.itemMovieBinding.setItem(myViewModel);
            this.itemMovieBinding.executePendingBindings();
        }
    }
}
