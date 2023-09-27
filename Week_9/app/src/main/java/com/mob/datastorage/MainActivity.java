package com.mob.datastorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    public static final String PREFES_NAME = "prefeName";
    public static final String USER_NAME = "userName";
    public static final String DARK_MODE = "darkMode";

    private TextView textName;
    private EditText editName;
    private SwitchCompat darkMode;
    private Button buttonSave;

    private String name;
    private boolean switchOnOff;
    private boolean isSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textName = findViewById(R.id.textName);
        editName = findViewById(R.id.editName);
        darkMode = findViewById(R.id.darkMode);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (darkMode.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        loadData();
        updateView();
        if(savedInstanceState == null) {
            updateDarkMode();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.internalStorage) {
            Intent intent = new Intent(this, InternalStorage.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.externalStorage) {
            Intent myIntent = new Intent(this, ExternalStorage.class);
            startActivity(myIntent);
            return true;
        }

        if (id == R.id.sqliteStorage) {
            Intent myIntent = new Intent(this, SqliteStorage.class);
            startActivity(myIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void saveData() {
        textName.setText("User name: " + editName.getText());

        SharedPreferences preferences = getSharedPreferences(PREFES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, editName.getText().toString());
        editor.putBoolean(DARK_MODE, darkMode.isChecked());

        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

        editor.apply();

    }

    private void loadData() {
        SharedPreferences preferences = getSharedPreferences(PREFES_NAME, MODE_PRIVATE);
        switchOnOff = preferences.getBoolean(DARK_MODE, false);
        name = preferences.getString(USER_NAME, "");
    }

    private void updateView() {
        darkMode.setChecked(switchOnOff);
        textName.setText("User name: " + name);
    }

    private void updateDarkMode() {
        if (darkMode.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}