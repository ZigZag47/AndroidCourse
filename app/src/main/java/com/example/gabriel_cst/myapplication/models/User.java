package com.example.gabriel_cst.myapplication.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Ignore
    public List<Movie> movies;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        movies = new ArrayList<>();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject userJSON = new JSONObject();

        userJSON.put("id", id);
        userJSON.put("first_name", firstName);
        userJSON.put("last_name", lastName);

        JSONArray moviesJsonArray = new JSONArray();
        for (Movie movie : movies) {
            moviesJsonArray.put(movie.toJson());
        }

        userJSON.put("movies", moviesJsonArray);

        return userJSON;
    }

    public User fromJSON(String data) throws JSONException {

        JSONObject userJson = new JSONObject(data);

        int id = userJson.getInt("id");
        String firstName = userJson.getString("first_name");
        String lastName = userJson.getString("last_name");

        List<Movie> movies = new ArrayList<>();

        JSONArray moviesList = userJson.getJSONArray("movies");
        for(int index = 0; index < moviesList.length(); index++) {
            JSONObject movieJSON = moviesList.getJSONObject(index);

            movies.add(new Movie().fromJSON(movieJSON));
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;

        return this;
    }
}