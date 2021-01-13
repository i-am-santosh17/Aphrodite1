package com.example.aphrodite;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subject_table")
public class Subject{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="subject_name")
    private  String mSubject;
    public  Subject(@NonNull String subject){

        this.mSubject=subject;
    }

    public String getSubject(){
        return this.mSubject;
    }


}

