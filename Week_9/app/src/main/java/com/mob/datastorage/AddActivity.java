package com.mob.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText editTask, editNote;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTask = findViewById(R.id.task);
        editNote = findViewById(R.id.note);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedReaderDbHelper myDB = new FeedReaderDbHelper(AddActivity.this);
                myDB.addTask(editTask.getText().toString().trim(),
                        editNote.getText().toString().trim());
                }
        });
    }
}