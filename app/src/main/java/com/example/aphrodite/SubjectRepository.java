package com.example.aphrodite;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectRepository{
    private SubjectDao mSubjectDao;
    private LiveData<List<Subject>> mAllSubjects;
    LiveData<List<String>> mAllNotes;
    LiveData<List<String>> mAllWNotes;

    // constuctor to get the database and initialize Dao and also some other things
    SubjectRepository(Application application){
        SubjectRoomDatabase db=SubjectRoomDatabase.getDatabase(application);
        mSubjectDao=db.subjectDao();
        mAllSubjects=mSubjectDao.getAllSubjects();
    }
    LiveData<List<Subject>> getAllSubjects(){
        return mAllSubjects;
    }

    LiveData<List<String>> getAllNotes(String subjec){
        mAllNotes=mSubjectDao.getAllNotes(subjec);
        return  mAllNotes;
    }
    LiveData<List<String>> getmAllWNotes(String subjec){
        mAllWNotes=mSubjectDao.getAllNotesw(subjec);
        return mAllWNotes;
    }

// function to insert subject into subject
    public void insert(Subject subject){
        new insertAsyncTask(mSubjectDao).execute(subject);
    }

    // insert notes into notes table
    public void insertn(Notes note){
        new insertAsyncTaskk(mSubjectDao).execute(note);
    }
    // insert into weel table
    public void insertw(WeekNotes note){
        new insertAsyncTaskkK(mSubjectDao).execute(note);
    }

    public void deleteAllNotes(){
        new deleteNotesAsyncTaskk(mSubjectDao).execute();
    }

    public void deleteWeekNotes(){
        new deleteWeekNotesAsyncTaskk(mSubjectDao).execute();
    }
// Asynchronous task to insert subject
   private static  class insertAsyncTask extends AsyncTask<Subject,Void,Void> {
            private SubjectDao mAsyncTaskDao;
            insertAsyncTask(SubjectDao dao){
            mAsyncTaskDao =dao;
}

       @Override
       protected Void doInBackground( final Subject... params) {
           mAsyncTaskDao.insert(params[0]);
           return null;
       }
   }
   private static class insertAsyncTaskk extends AsyncTask<Notes,Void,Void>{
        private SubjectDao mAsynctaskDao;
        insertAsyncTaskk(SubjectDao dao){
            mAsynctaskDao=dao;
        }

       @Override
       protected Void doInBackground(Notes... notes) {
            mAsynctaskDao.insertn(notes[0]);
           return null;
       }
   }
    private static class insertAsyncTaskkK extends AsyncTask<WeekNotes,Void,Void>{
        private SubjectDao mAsynctaskDao;
        insertAsyncTaskkK(SubjectDao dao){
            mAsynctaskDao=dao;
        }

        @Override
        protected Void doInBackground(WeekNotes... notes) {
            mAsynctaskDao.insertw(notes[0]);
            return null;
        }
    }
    private static class deleteNotesAsyncTaskk extends AsyncTask<Void,Void,Void>{
        private SubjectDao mAsynctaskDao;
        deleteNotesAsyncTaskk(SubjectDao dao){
            mAsynctaskDao=dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsynctaskDao.deleteNotes();
            return null;
        }


    }
    private static class deleteWeekNotesAsyncTaskk extends AsyncTask<Void,Void,Void>{
        private SubjectDao mAsynctaskDao;
        deleteWeekNotesAsyncTaskk(SubjectDao dao){
            mAsynctaskDao=dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsynctaskDao.deleteweekNotes();
            return null;
        }

    }
}

