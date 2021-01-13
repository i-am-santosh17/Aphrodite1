package com.example.aphrodite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.aphrodite.MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;

public class MainActivity2 extends AppCompatActivity {
    public static final String EXTRA_MESSAG =
            "com.example.aphrodite.extra.MESSAGE";
    String subjec;
private SubjectViewModel mNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent=getIntent();
 subjec=intent.getStringExtra(MainActivity.EXTRA_MESSAGE).toString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView=findViewById(R.id.note_list_day);
        final NoteListAdapter adapter=new NoteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNoteViewModel= ViewModelProviders.of(this).get(SubjectViewModel.class);
       mNoteViewModel.getAllNotes(subjec).observe(this, new Observer<List<String>>() {
           @Override
           public void onChanged(List<String> notes) {

              adapter.setNotes(notes);

           }
       });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Toast.makeText(
                    getApplicationContext(),
                    data.getStringExtra(EnterNotes.EXTRA_REPLY),
                    Toast.LENGTH_LONG).show();
            Notes note=new Notes();

            note.mNote=data.getStringExtra(EnterNotes.EXTRA_REPLY);
            note.subject=subjec;

            WeekNotes notew=new WeekNotes();
            notew.mNote=data.getStringExtra(EnterNotes.EXTRA_REPLY);
            notew.subject=subjec;

            Log.d("LOG_TAG",note.mNote);
            //Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mNoteViewModel.insertn(note);
            mNoteViewModel.insertw(notew);
        } else {
            Toast.makeText(
                    getApplicationContext(), "Note not saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void addNotes(View view) {
        Intent intent=new Intent(this,EnterNotes.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }


    public void thisWeek(View view) {
        Intent intent=new Intent(this,MainActivity3.class);
        intent.putExtra(EXTRA_MESSAG,subjec);
        startActivity(intent);
    }

    public void clearNotes(View view) {
        Warning war=new Warning();
      war.show(getSupportFragmentManager(),"DELETE NOTES");


    }
}