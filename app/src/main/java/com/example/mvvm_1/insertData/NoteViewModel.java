package com.example.mvvm_1.insertData;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteDao noteDao;
    private NoteRoomDatabase database;
    private LiveData<List<Note>> getAllNoteslist;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        database=NoteRoomDatabase.getRoomDatabase(application);
        noteDao=database.mNoteDao();
        getAllNoteslist=noteDao.getAllNotes();
    }
    public  LiveData<List<Note>> getAllNotesDAta(){
        return getAllNoteslist;
    }
  
    public void Insert(Note note)
    {
     new InsertAsyncTask(noteDao).execute(note);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class InsertAsyncTask  extends AsyncTask<Note,Void,Void> {
       
        NoteDao noteDao;
        public InsertAsyncTask(NoteDao noteDao) {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... note) {
                noteDao.insert(note[0]);
                return null;
        }
    }
}
