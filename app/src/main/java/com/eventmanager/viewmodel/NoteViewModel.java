package com.eventmanager.viewmodel;

import android.app.Application;

import com.eventmanager.model.repository.NoteRepository;
import com.eventmanager.model.entity.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    //we dont store the context of an activity or a view
    //that refs an activity in the viewmodel, because
    //viewmodel is designed to outlive an activity after its destroyed
    //so holding a ref to a destroyed activity causes memory leak
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
