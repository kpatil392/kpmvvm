package com.example.mvvm_1.insertData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,version =1)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public abstract NoteDao mNoteDao();
   
    
    
    private static volatile NoteRoomDatabase noteRomInstance;
    public static NoteRoomDatabase getRoomDatabase(final Context context)
    {
        if(noteRomInstance==null)
        {
            synchronized (NoteRoomDatabase.class){
                if(noteRomInstance==null){
                    noteRomInstance= Room.databaseBuilder(context.getApplicationContext(),NoteRoomDatabase.class,"notedabases").build();
                }
            }
        }
        return noteRomInstance;
    }


  
}
