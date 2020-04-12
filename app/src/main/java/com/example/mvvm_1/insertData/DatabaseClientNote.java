package com.example.mvvm_1.insertData;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClientNote {
    private Context mCtx;
    private static DatabaseClientNote mInstance;

    //our app database object
    private AppDatabaseNote appDatabaseNote;

    private DatabaseClientNote(Context mCtx) {
        this.mCtx = mCtx;
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabaseNote = Room.databaseBuilder(mCtx, AppDatabaseNote.class, "ffffff").build();
    }

    public static synchronized DatabaseClientNote getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClientNote(mCtx);
        }
        return mInstance;
    }

    public AppDatabaseNote getAppDatabaseNote() {
        return appDatabaseNote;
    }
}
