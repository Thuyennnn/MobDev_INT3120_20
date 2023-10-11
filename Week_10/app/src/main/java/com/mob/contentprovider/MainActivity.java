package com.mob.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver resolver = this.getContentResolver();

        Uri userDictionaryUri = UserDictionary.Words.CONTENT_URI;

        String[] projection = {
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD,
                UserDictionary.Words.FREQUENCY
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Cursor cursor = resolver.query(userDictionaryUri, projection, selection, selectionArgs, sortOrder);

// Xử lý dữ liệu từ cursor
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {

                    int wordId = cursor.getInt(Math.abs(cursor.getColumnIndex(UserDictionary.Words._ID)));
                    String word = cursor.getString(Math.abs(cursor.getColumnIndex(UserDictionary.Words.WORD)));
                    int frequency = cursor.getInt(Math.abs(cursor.getColumnIndex(UserDictionary.Words.FREQUENCY)));

                  Log.d("content provider", Integer.toString(wordId) + " " + word + "" + Integer.toString(frequency));
                }
            } finally {
                cursor.close(); // Đảm bảo đóng cursor sau khi hoàn thành
            }
        }
    }
}