package com.mob.sqlitedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mob.sqlitedemo.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("select * from user")
    List<User> getAllUser();

    @Query("select * from user where name = :userName")
    List<User> checkUserNameExisted(String userName);

    @Update
    void updateUser(User user);
}
