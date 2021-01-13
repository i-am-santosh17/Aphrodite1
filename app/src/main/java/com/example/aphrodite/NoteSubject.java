package com.example.aphrodite;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class NoteSubject{
    @Embedded public Subject subject;
    @Relation(
            parentColumn = "subject_name",
            entityColumn = "subject"
    )
    public List<Notes> note;

}
