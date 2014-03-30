package com.example.app.resources;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.example.app.models.User;

import retrofit.RestAdapter;

/**
 * Created by root on 3/23/14.
 */
public class Global extends Application {

    private static final String API_URL = "http://67.165.80.24:8000";
    private static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .build();
    private static HJClient client = restAdapter.create(HJClient.class);
    private static User user = new User();

    private static Context context;
    private static Database database;
    private static LocationManager locationManager = null;

    public static User getUser() {
        return user;
    }
    public static void setUser(User newUser) {
        user = newUser;
    }

    public static void newInstance(LocationManager locMan) {
        database = new Database(context);
        locationManager = locMan;
    }
    public static Database getDatabase() {
        return database;
    }

    public static void setContext(Context con) {
        context = con;
    }
    public static Context getContext() {
        return context;
    }

    public static HJClient getClient() {
        return client;
    }

    public static String getRootUrl() {
        return API_URL;
    }

    public static LocationManager getLocationManager() {
        return locationManager;
    }
}