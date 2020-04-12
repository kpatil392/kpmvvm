package com.example.mvvm_1.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_1.insertData.NoteDao;
import com.example.mvvm_1.insertData.NoteRoomDatabase;

public class TaskViewModel extends AndroidViewModel {
    private Task mTask;
    private DatabaseClient mDatabaseClient;
    private TaskDao mTaskDao;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        mDatabaseClient=DatabaseClient.getInstance(application);
        //mTaskDao=AppDatabase.TaskDao
    }
}
