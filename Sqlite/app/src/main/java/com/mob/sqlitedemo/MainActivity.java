package com.mob.sqlitedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mob.sqlitedemo.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    EditText edtUserName;
    EditText edtAddress;
    Button btnAddUser;
    RecyclerView rcvUser;

    private UserAdapter userAdapter;
    private List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        userAdapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void UpdateUser(User user) {
                updateUser(user);
            }
        });
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        rcvUser.setAdapter(userAdapter);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    private void updateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    public void addUser() {
        String userName = edtUserName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(address)) {
            return;
        }

        if(checkUserNameExisted(userName) == true) {
            return;
        }

        User user = new User(userName,address);
        UserDatabase.getInstance(this).userDao().insert(user);
        loadData();
        Toast.makeText(this, "add success", Toast.LENGTH_SHORT).show();

        edtUserName.setText("");
        edtAddress.setText("");
        hideKeyboard();



    }

    public void loadData() {
        mListUser = UserDatabase.getInstance(this).userDao().getAllUser();
        userAdapter.setData(mListUser);

        User user = mListUser.get(0);
        Log.d("sqlite debug", "main " + user.getAddress());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }



    private boolean checkUserNameExisted(String userName) {
        List<User> users = UserDatabase.getInstance(this).userDao().checkUserNameExisted(userName);

        return users != null && !users.isEmpty();
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void initUI() {
        edtUserName = findViewById(R.id.edtUserName);
        edtAddress = findViewById(R.id.edtAddress);
        btnAddUser = findViewById(R.id.btnAddUser);
        rcvUser = findViewById(R.id.rcvUser);
    }
}