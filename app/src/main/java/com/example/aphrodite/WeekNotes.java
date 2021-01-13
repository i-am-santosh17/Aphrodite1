package com.example.aphrodite;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeekNotes{
    @NonNull
    @PrimaryKey
    public String mNote;
    public String subject;
}
