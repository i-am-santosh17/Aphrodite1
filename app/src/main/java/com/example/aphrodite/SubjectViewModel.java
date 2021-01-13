package com.example.aphrodite;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {
private SubjectRepository mSubjectRepository;
private LiveData<List<Subject>> mAllSubjects;
 public LiveData<List<String>> mAllNotes;
public LiveData<List<String>> mAllWNotes;
    public SubjectViewModel(@NonNull Application application) {
        super(application);
        mSubjectRepository= new SubjectRepository(application);
        mAllSubjects=mSubjectRepository.getAllSubjects();

    }
    // getting subject list
    LiveData<List<Subject>> getAllSubjects(){
        return mAllSubjects;
    }
    // getting notes
    LiveData<List<String>> getAllNotes(String subjec){
        mAllNotes=mSubjectRepository.getAllNotes(subjec);

        return mAllNotes;
    }
    LiveData<List<String>> getmAllWNotes(String subjec){
        mAllWNotes=mSubjectRepository.getmAllWNotes(subjec);
        return mAllWNotes;
    }

    // inssrting subject
    public void insert(Subject subject){
        mSubjectRepository.insert(subject);
    }

public void insertn(Notes note){
        mSubjectRepository.insertn(note);
}

// insert nito weeknote table
public void insertw(WeekNotes note){
        mSubjectRepository.insertw(note);
}
public void deleteNotes(){
        mSubjectRepository.deleteAllNotes();
}
public void deleteWeekNotes(){
        mSubjectRepository.deleteWeekNotes();
}
}
