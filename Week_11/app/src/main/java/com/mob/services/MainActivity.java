package com.mob.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BoundService boundService;
    private boolean isServiceConnected;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) iBinder;
            boundService = myBinder.getBoundService();
            boundService.startMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = true;
        }
    };

    private EditText edtNotice;
    private Button btnStartService;
    private Button btnStopService;
    private Button btnStartBoundService;
    private Button btnStopBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNotice = findViewById(R.id.edtNotice);
        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        btnStartBoundService = findViewById(R.id.btnStartBoundService);
        btnStopBoundService = findViewById(R.id.btnStopBoundService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(v);
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(v);
            }
        });

        btnStartBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBoundService(v);
            }
        });

        btnStopBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBoundService(v);
            }
        });
    }

    public void startService(View v) {
        String input = edtNotice.getText().toString();

        Context context = getApplicationContext();
        Intent intent = new Intent(MainActivity.this, HelloService.class);
        intent.putExtra("notice", input);
        context.startForegroundService(intent);
        Toast.makeText(this, "start force service", Toast.LENGTH_SHORT).show();
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, HelloService.class);
        stopService(serviceIntent);
        Toast.makeText(this, "stop force service", Toast.LENGTH_SHORT).show();
    }
    public void startBoundService(View v) {
       Intent intent = new Intent(this, BoundService.class);
       bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    public void stopBoundService(View v) {
      if(isServiceConnected) {
          unbindService(serviceConnection);
          isServiceConnected = false;
      }
    }
}