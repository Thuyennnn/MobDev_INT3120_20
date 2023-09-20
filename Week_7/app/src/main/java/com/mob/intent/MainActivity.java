package com.mob.intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextFullName;
    Button buttonSendMessage;
    TextView textFeedback;

    Button moveOnImpicitIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFullName = findViewById(R.id.fullName);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);
        textFeedback = findViewById(R.id.textView3);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        moveOnImpicitIntent = findViewById(R.id.moveOnImpicitItent);
        moveOnImpicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImplicitIntentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendMessage() {
        String fullName = editTextFullName.getText().toString();
        String message = "Hello, Please say hello me!";

        Intent intent = new Intent(this, GreatingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);
        activityLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    String feedback = data.getStringExtra("feedback");
                    textFeedback.setText(feedback);

                } else {
                    textFeedback.setText("????  " + Integer.toString(result.getResultCode()));
                }
            });
}