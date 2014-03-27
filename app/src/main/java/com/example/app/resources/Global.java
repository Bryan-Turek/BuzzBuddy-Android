package com.example.app.resources;

import android.app.Application;
import android.content.Context;

import com.example.app.models.User;

/**
 * Created by root on 3/23/14.
 */
public class Global extends Application {

    private static final String API_URL = "http://67.165.80.24:8000";
    private static HJClient client = new HJClient();
    private static User user = new User();

    private static Global instance;

    public Global() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public static HJClient getClient() {
        return client;
    }
}
