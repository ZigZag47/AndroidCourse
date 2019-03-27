package com.example.gabriel_cst.myapplication.helpers.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import com.example.gabriel_cst.myapplication.ApplicationController;
import com.example.gabriel_cst.myapplication.interfaces.OnUserRepositoryActionListener;
import com.example.gabriel_cst.myapplication.models.User;

public class UserRepository {

    private AppDatabase appDatabase;
    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user,
                           final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(user);
    }

    public User getUserByName(String firstName, String lastName){
        return appDatabase.userDao().findByName(firstName, lastName);
    }

   private class InsertTask extends AsyncTask<User, Void, Void> {

       OnUserRepositoryActionListener listener;

        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().insertTask(users[0]);
            return null;
        }

       @Override
       protected void onPostExecute(Void aVoid) {
           super.onPostExecute(aVoid);

           listener.actionSuccess();
       }
   }
}
