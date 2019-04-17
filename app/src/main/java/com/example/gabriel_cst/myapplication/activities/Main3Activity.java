package com.example.gabriel_cst.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.gabriel_cst.myapplication.R;
import com.example.gabriel_cst.myapplication.tasks.MyReceiver;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myReceiver = new MyReceiver();

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.gabriel_cst.myapplication.activities.CUSTOM_ACTION");
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.gabriel_cst.myapplication.activities.CUSTOM_ACTION");
        sendBroadcast(intent);
    }


    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }

}
