package com.example.app.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 3/4/14.
 */
public class Event {

    @SerializedName("_id")
    private String _id;

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    public Event Event() {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return _id;
    }

    public String getDescription() {
        return description;
    }
}