package com.example.app.resources.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.app.models.Event;
import com.example.app.resources.Global;
import com.example.app.resources.adapters.EventArrayAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EventsListFragment extends ListFragment {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Global.getClient().events(new Callback() {

            @Override
            public void success(Object o, Response response) {
                List<Event> events = (List<Event>) o;
                EventArrayAdapter adapter = new EventArrayAdapter(getActivity(), events);
                setListAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // do something with the data
    }

}