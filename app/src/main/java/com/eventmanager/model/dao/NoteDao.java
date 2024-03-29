package com.eventmanager.model.dao;


import com.eventmanager.model.entity.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//Data Access Objects
//Defining database operations to make on the note entity

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    //livedata observes the changes in the note table and notifies activity
    LiveData<List<Note>> getAllNotes();//room auto generates the code for these methods

}
