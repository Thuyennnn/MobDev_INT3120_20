package com.mob.week_12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Button btnStartSensor;
    Button btnStopSensor;

    TextView textView;

    Toolbar toolbar;

    private long lastSensorUpdateTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        btnStartSensor = findViewById(R.id.btn_start_sensor);
        btnStopSensor = findViewById(R.id.btn_stop_sensor);
        textView = findViewById(R.id.tv_sensor_information);

        btnStartSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensor();
            }
        });

        btnStopSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSensor();
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.wifi) {
            Intent intent = new Intent(MainActivity.this, WifiActivity.class);
            startActivity(intent);
        } else if(id == R.id.camera) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSensor() {
        Sensor magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticFieldSensor != null) {
            sensorManager.registerListener(magneticFieldListener, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void stopSensor() {
        sensorManager.unregisterListener(magneticFieldListener);
        textView.setText("");
    }

    private SensorEventListener magneticFieldListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            long currentTime = System.currentTimeMillis();

            if (currentTime - lastSensorUpdateTime >= 1000) {
                float x = event.values[0]; // Giá trị từ trường theo trục x
                float y = event.values[1]; // Giá trị từ trường theo trục y
                float z = event.values[2]; // Giá trị từ trường theo trục z

                String information = Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z);

                Log.d("sensor", information);
                textView.setText(information);
                lastSensorUpdateTime = currentTime;
            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Xử lý sự thay đổi độ chính xác của cảm biến (nếu cần)
        }
    };

}