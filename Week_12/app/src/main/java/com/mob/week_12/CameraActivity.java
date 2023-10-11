package com.mob.week_12;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnTakeAPhoto;

    VideoView videoView;
    Button btnTakeAVideo;

    ActivityResultLauncher<Intent> takePhotoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result->{
                Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                imageView.setImageBitmap(photo);
    });

    ActivityResultLauncher<Intent> takeVideoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result-> {
                videoView.setVideoURI(result.getData().getData());
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imv_image);
        btnTakeAPhoto = findViewById(R.id.btn_take_photo);
        videoView = findViewById(R.id.vdv_videoView);
        btnTakeAVideo = findViewById(R.id.btn_take_video);

        btnTakeAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoLauncher.launch(intent);
            }
        });

        btnTakeAVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                takeVideoLauncher.launch(intent);
            }
        });


    }
}