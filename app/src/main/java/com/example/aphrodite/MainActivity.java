package com.example.aphrodite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.example.aphrodite.extra.MESSAGE";
private SubjectViewModel mSubjectViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView =findViewById(R.id.subject_list);

        final SubjectListAdapter adapter=new SubjectListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSubjectViewModel= ViewModelProviders.of(this).get(SubjectViewModel.class);

        mSubjectViewModel.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects)
            {
                adapter.setSubjects(subjects);

            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        RecyclerView.ViewHolder rv_view = recyclerView.findViewHolderForAdapterPosition(position);
                        TextView iv_wish = rv_view.itemView.findViewById(R.id.textView);
                        String s=iv_wish.getText().toString();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra(EXTRA_MESSAGE,s);
                         startActivity(intent);
                        Toast.makeText(
                                getApplicationContext(),
                                s,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Subject subject= new Subject(data.getStringExtra(NewSubjectActivity.EXTRA_REPLY));
            mSubjectViewModel.insert(subject);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void addSubject(View view) {
        Intent intent = new Intent(MainActivity.this, NewSubjectActivity.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }


}

