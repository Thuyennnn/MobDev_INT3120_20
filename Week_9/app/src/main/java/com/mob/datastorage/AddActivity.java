package com.mob.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                myDB.addBook(editTask.getText().toString().trim(),
                        editNote.getText().toString().trim());
            }
        });
    }
}