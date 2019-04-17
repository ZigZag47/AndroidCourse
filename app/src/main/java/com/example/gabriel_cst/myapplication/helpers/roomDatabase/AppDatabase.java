package com.example.gabriel_cst.myapplication.helpers.roomDatabase;

import com.example.gabriel_cst.myapplication.interfaces.UserDao;
import com.example.gabriel_cst.myapplication.models.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}