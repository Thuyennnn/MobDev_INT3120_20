package com.mob.media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnPlayMusic;
    Button btnStopMusic;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_media);
        setSupportActionBar(toolbar);

        btnPlayMusic = findViewById(R.id.btn_play_music);
        btnStopMusic = findViewById(R.id.btn_stop_music);
        btnPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        btnStopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });

    }

    private void playMusic() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
        mediaPlayer.start();
    }

    private void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.location) {
            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}