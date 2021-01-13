package com.example.aphrodite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewSubjectActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY="com.example.aphrodite.REPLY";
    private EditText mEditSubjectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        mEditSubjectView=findViewById(R.id.edit_subject);
        final Button button=findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent=new Intent();
                if(TextUtils.isEmpty(mEditSubjectView.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                } else{
                    String subject =mEditSubjectView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,subject);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
    }
}