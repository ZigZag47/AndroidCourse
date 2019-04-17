package com.example.gabriel_cst.myapplication.helpers.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import com.example.gabriel_cst.myapplication.ApplicationController;
import com.example.gabriel_cst.myapplication.interfaces.OnUserRepositoryActionListener;
import com.example.gabriel_cst.myapplication.models.User;

import java.util.List;

//A Repository mediates between the domain and data mapping layers,
// acting like an in-memory domain object collection.
// We access the database class and the DAO class from the repository and perform list of operations
// such as insert, update, delete, get etc.
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

   private class InsertTask extends AsyncTask<User, Void, List<User>> {

       OnUserRepositoryActionListener listener;

        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

       @Override
       protected void onPreExecute() {
           super.onPreExecute();

       }

       @Override
        protected List<User> doInBackground(User... users) {
            return appDatabase.userDao().getAll();
        }

       @Override
       protected void onPostExecute(List<User> aVoid) {
           super.onPostExecute(aVoid);

           listener.actionSuccess();
       }
   }
}
