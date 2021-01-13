package com.example.aphrodite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;


@Dao
public interface SubjectDao{

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Subject subject);

           // function to insert notes into table


        @Query("DELETE FROM subject_table")
        void deleteAll();

        @Query("SELECT * from subject_table")
       LiveData<List<Subject>> getAllSubjects();

        @Transaction
     @Query("SELECT mNote from Notes WHERE subject=:sub")
     public LiveData<List<String>> getAllNotes(String sub);

        @Query("SELECT mNote from WeekNotes WHERE subject=:sub")
        public LiveData<List<String>> getAllNotesw(String sub);

        @Insert()
    void insertn(Notes note);
@Insert
    void insertw(WeekNotes note);

@Query("DELETE FROM Notes")
    void deleteNotes();

@Query("DELETE FROM WeekNotes")
    void deleteweekNotes();

        }
