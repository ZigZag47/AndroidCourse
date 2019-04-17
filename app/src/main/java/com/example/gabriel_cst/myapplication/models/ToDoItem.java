package com.example.gabriel_cst.myapplication.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoItem {

    public long id;
    public long userId;

    private String title;
    private Boolean completed;

    public ToDoItem fromJSON(JSONObject userJson) throws JSONException {
        int id = userJson.getInt("id");
        int userId = userJson.getInt("userId");
        String title = userJson.getString("title");
        Boolean completed = userJson.getBoolean("completed");


        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;

        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}