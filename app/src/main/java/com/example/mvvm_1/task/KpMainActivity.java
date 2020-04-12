package com.example.mvvm_1.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.KpActivityMainBinding;

import java.util.List;

public class KpMainActivity extends AppCompatActivity {
    KpActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(KpMainActivity.this, R.layout.kp_activity_main);
        binding.recyclerviewTasks.setLayoutManager(new LinearLayoutManager(this));
        binding.floatingButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KpMainActivity.this, KpAddTaskActivity.class);
                startActivity(intent);
            }
        });
        getTasks();
    }
    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(KpMainActivity.this, tasks);
                binding.recyclerviewTasks.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}