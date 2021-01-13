package com.example.aphrodite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    String subjec;
    private SubjectViewModel mNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        subjec=intent.getStringExtra(MainActivity2.EXTRA_MESSAG).toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        RecyclerView recyclerView=findViewById(R.id.week_list);
        final WeekListAdapter adapter=new WeekListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNoteViewModel= ViewModelProviders.of(this).get(SubjectViewModel.class);
        mNoteViewModel.getmAllWNotes(subjec).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setNotes(strings);
            }
        });
    }

    public void clearNotes(View view) {
        WarningWeek war=new WarningWeek();
        war.show(getSupportFragmentManager(),"DELETE NOTES");

    }
}
