package com.example.app;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.app.models.Event;
import com.example.app.models.User;
import com.example.app.resources.Global;
import com.example.app.resources.adapters.EventArrayAdapter;
import com.example.app.resources.fragments.NavigationDrawerFragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpdateFeed extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        android.location.LocationListener {

    private final User user = Global.getUser();
    private final static int
            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private EventsListTask mEventsListTask = null;

    private ListView mListView = null;
    private EventArrayAdapter mEventAdapter = null;

//    private GoogleMap mMap = null;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Global.setContext(getApplicationContext());
        Global.newInstance((LocationManager) getSystemService(LOCATION_SERVICE));

        if(user.getIsLoggedIn()) {
            setContentView(R.layout.activity_update_feed);
            mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));

            mListView = (ListView) findViewById(R.id.listView);

            mEventsListTask = new EventsListTask();
            mEventsListTask.execute();

//            mContainer = findViewById(R.id.container);
//            mLoadStatus = findViewById(R.id.load_status);

//            mMap = ((MapFragment) getFragmentManager()
//                    .findFragmentById(R.id.map)).getMap();
//
//            LocationManager locMan = Global.getLocationManager();
//            Location location = locMan.getLastKnownLocation(locMan.getBestProvider(new Criteria(), true));
//            LatLng mLocation;
//            if(location != null) {
//                mLocation = new LatLng(location.getLatitude(), location.getLongitude());
//            } else {
//                mLocation = new LatLng(39.63, -79.956);
//            }
//
//            mMap.setMyLocationEnabled(true);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 13));

        } else {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
//        mEventsListTask = new EventsListTask();
//        mEventsListTask.execute();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.update_feed);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.update_feed, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class EventsListTask extends AsyncTask<Void, Void, Void> {

        private EventsListTask mEventsListTask = null;

        @Override
        protected Void doInBackground(Void... voids) {
            Global.getClient().events(new Callback() {

                @Override
                public void success(Object o, Response response) {
                    mEventAdapter = new EventArrayAdapter(getBaseContext(), (List<Event>) o);
                    mListView.setAdapter(mEventAdapter);
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            });
            return null;
        }

        @Override
        protected void onCancelled() {
            mEventsListTask = null;
            //showProgress(false);
        }
    }
}
