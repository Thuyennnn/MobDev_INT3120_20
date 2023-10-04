package com.mob.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BoundService extends Service {

    private MyBinder myBinder = new MyBinder();
    private MediaPlayer mediaPlayer;

    public class MyBinder extends Binder {
        BoundService getBoundService() {
            return BoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service", "on create bound service");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "on bind bound service");
        return myBinder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("service", "on unbind bound service");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("service", "on destroy bound service");
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void startMusic() {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ring);
        }
        mediaPlayer.start();
    }
}
