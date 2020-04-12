package com.example.mvvm_1.insertData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvvm_1.task.Task;
import com.example.mvvm_1.task.TaskDao;

@Database(entities = {Note.class},version = 1)
public abstract class AppDatabaseNote extends RoomDatabase {
    public abstract NoteDao mNoteDao();
}
