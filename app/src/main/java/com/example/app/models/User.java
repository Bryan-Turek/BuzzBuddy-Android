package com.example.app.models;

import com.example.app.resources.Global;
import com.example.app.resources.HJClient;
import com.google.gson.annotations.SerializedName;
/**
 * Created by root on 3/4/14.
 */
public class User {

    @SerializedName("_id")
    private String _id;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;

    @SerializedName("role")
    private String role;

    @SerializedName("verified")
    private Boolean verified;

    private Boolean isLoggedIn = false;
    private HJClient client = Global.getClient();

    public User User() {
        return this;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        this.isLoggedIn = true;
    }
}
