package com.mob.week_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WifiActivity extends AppCompatActivity {

    Button btnTurnOnWifi;
    Button btnTurnOffWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        btnTurnOnWifi = findViewById(R.id.btn_turn_on_wifi);
        btnTurnOffWifi = findViewById(R.id.btn_turn_off_wifi);

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        btnTurnOnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManager.setWifiEnabled(true);
                Toast.makeText(WifiActivity.this, "turn on wifi", Toast.LENGTH_SHORT).show();
            }
        });

        btnTurnOffWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManager.setWifiEnabled(false);
                Toast.makeText(WifiActivity.this, "turn off wifi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}