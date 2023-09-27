package com.mob.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ImplicitIntentActivity extends AppCompatActivity {

    Button ggSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // google search



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ggSearch) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn/"));
            startActivity(intent);
            return true;
        }

        if (id == R.id.actionDial) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0334022754"));
            startActivity(intent);
            return true;
        }
        if (id == R.id.contact) {
            String myData = "content://contacts/people/";
            Intent myActivity2 = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
            startActivity(myActivity2);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}