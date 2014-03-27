package com.example.app.models;

import com.example.app.resources.Global;
import com.example.app.resources.HJClient;
import com.google.gson.annotations.SerializedName;
/**
 * Created by root on 3/4/14.
 */
public class Events {

    @SerializedName("_id")
    private String _id;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;
    private HJClient client = Global.getClient();

    public Events Events() {
        return this;
    }

}