package com.example.gabriel_cst.myapplication;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.Bundle;

import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.helpers.roomDatabase.AppDatabase;

public class ApplicationController extends Application {

    private static ApplicationController mInstance;

    private static AppDatabase mAppDatabase;

    public static ApplicationController newInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

         mInstance = this;

         mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                 AppDatabase.class, Constants.DB_NAME).build();
    }

    public static AppDatabase getAppDatabase(){
        return mAppDatabase;
    }
}
