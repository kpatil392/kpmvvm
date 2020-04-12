package com.example.mvvm_1.insertData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ddd")
public class Note {
    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String notename;

    public Note(@NonNull String id, @NonNull String notename) {
        this.id = id;
        this.notename = notename;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getNotename() {
        return notename;
    }
}
