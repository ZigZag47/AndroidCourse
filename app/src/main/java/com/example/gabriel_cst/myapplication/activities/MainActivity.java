package com.example.gabriel_cst.myapplication.activities;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriel_cst.myapplication.R;
import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.helpers.roomDatabase.UserRepository;
import com.example.gabriel_cst.myapplication.interfaces.OnUserRepositoryActionListener;
import com.example.gabriel_cst.myapplication.models.User;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myBtn = findViewById(R.id.btn_myBtn1);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        Main2Activity.class);
                finish();
                startActivity(intent);
            }
        });

        Button myBtn2 = findViewById(R.id.btn_myBtn2);
        myBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        Main2Activity.class);

                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });

        Button myBtnAddUser = findViewById(R.id.btn_addUser);
        myBtnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserToDatabase();
            }
        });

        textViewHello = findViewById(R.id.tv_hello);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_CODE && resultCode == Constants.RESULT_CODE) {
            if(data != null) {
                String title = data.getStringExtra(Constants.ARG_MY_KEY);
                textViewHello.setText(title);
            }
        }

    }

    private void addUserToDatabase() {
        User mUser = new User("Gabriel", "Alexandru");
        new UserRepository(MainActivity.this)
                .insertTask(mUser, new OnUserRepositoryActionListener() {
                    @Override
                    public void actionSuccess() {
                        Toast.makeText(MainActivity.this,
                                "Success added user!",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void actionFaailed() {

                    }
                });
    }

}
