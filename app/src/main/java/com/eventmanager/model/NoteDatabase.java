package com.eventmanager.model;

import android.content.Context;
import android.os.AsyncTask;

import com.eventmanager.model.dao.NoteDao;
import com.eventmanager.model.entity.Note;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    //make class a singleton
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();//Room subclasses the abstract classes

    //only one thread at a time can access this method,
    //so we dont accidentally create 2 instances when 2 threads try to access it
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback =  new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Event 1", "Description 1", 1));
            noteDao.insert(new Note("Event 2", "Description 2", 2));
            noteDao.insert(new Note("Event 3", "Description 3", 3));
            return null;
        }
    }

}
