package com.example.mvvm_1.task;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.DemoActivityBinding;

public class DemoActivity extends AppCompatActivity {
    DemoActivityBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(DemoActivity.this, R.layout.demo_activity);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               
           }
       });
    }

   
}
