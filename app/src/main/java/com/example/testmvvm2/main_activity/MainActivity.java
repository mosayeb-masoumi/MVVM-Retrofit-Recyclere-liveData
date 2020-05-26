package com.example.testmvvm2.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.testmvvm2.R;
import com.example.testmvvm2.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
      MyViewModel myViewModel = new MyViewModel(this);
      binding.setMovie(myViewModel);



    }
}
