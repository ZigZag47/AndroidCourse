package com.example.gabriel_cst.myapplication.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    public int id;
    private String name;
    private String description;

    public JSONObject toJson() throws JSONException {
        JSONObject movieJSON = new JSONObject();

        movieJSON.put("id", id);
        movieJSON.put("name", name);
        movieJSON.put("description", description);

        return movieJSON;
    }

    public Movie fromJSON(JSONObject movieJson) throws JSONException {
        int id = movieJson.getInt("id");
        String name = movieJson.getString("name");
        String description = movieJson.getString("description");

        this.id = id;
        this.name = name;
        this.description = description;

        return this;
    }

}
