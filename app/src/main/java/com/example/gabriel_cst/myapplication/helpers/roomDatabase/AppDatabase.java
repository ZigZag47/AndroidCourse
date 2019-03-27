package com.example.gabriel_cst.myapplication.helpers.roomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.gabriel_cst.myapplication.interfaces.UserDao;
import com.example.gabriel_cst.myapplication.models.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}