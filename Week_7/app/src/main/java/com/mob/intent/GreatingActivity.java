package com.mob.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GreatingActivity extends AppCompatActivity {

    TextView textViewMessage;
    Button backToMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greating);

        textViewMessage = findViewById(R.id.textView5);
        backToMainActivity = findViewById(R.id.backToMain);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        String fullName = intent.getStringExtra("fullName");
        textViewMessage.setText(message);

        backToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("feedback", "Ok Hello " + fullName + ". How are you?");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}