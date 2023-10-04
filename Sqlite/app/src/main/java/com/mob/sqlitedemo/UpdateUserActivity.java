package com.mob.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.sqlitedemo.database.UserDao;
import com.mob.sqlitedemo.database.UserDatabase;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtAddress;
    Button btnUpdateUser;

    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        edtUserName = findViewById(R.id.edtUserName);
        edtAddress = findViewById(R.id.edtAddress);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);

        mUser = (User) getIntent().getExtras().get("user");

        if(mUser != null) {
            edtUserName.setText(mUser.getName());
            edtAddress.setText(mUser.getAddress());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser() {

        String userName = edtUserName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(address)) {
            return;
        }

        mUser.setName(userName);
        mUser.setAddress(address);

        UserDatabase.getInstance(this).userDao().updateUser(mUser);
        User us = UserDatabase.getInstance(UpdateUserActivity.this).userDao().getAllUser().get(0);
        Log.d("sqlite debug", "update " + us.getAddress());
        Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}