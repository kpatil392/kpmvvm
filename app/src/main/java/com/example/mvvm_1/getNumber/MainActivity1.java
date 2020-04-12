package com.example.mvvm_1.getNumber;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.ActivityMainBinding;
import com.example.mvvm_1.databinding.GetnumberActivityMainBinding;

public class MainActivity1 extends AppCompatActivity {
    GetnumberActivityMainBinding binding;
    DataGenrater dataGenrater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity1.this, R.layout.activity_main);
        dataGenrater= ViewModelProviders.of(this).get(DataGenrater.class);
       // String data=dataGenrater.grtNumber().toString();
        LiveData<String> liveData=dataGenrater.grtNumber();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                 binding.tvone.setText(s);
            }
        });
        
        binding.btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataGenrater.createNumber();
            }
        });
    }
}
