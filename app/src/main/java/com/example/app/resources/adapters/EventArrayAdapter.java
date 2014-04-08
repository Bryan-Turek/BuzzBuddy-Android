package com.example.app.resources.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.models.Event;

import java.util.List;

public class EventArrayAdapter extends ArrayAdapter<Event> {
    private final LayoutInflater mInflater;
    private final List<Event> mEvents;

    public EventArrayAdapter(Context context, List<Event> data) {
        super(context, R.layout.event_item, data);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEvents = data;

    }

    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = mInflater.inflate(R.layout.event_item, parent, false);

        Event event = mEvents.get(position);
        ((TextView)view.findViewById(R.id.event_name)).setText(event.getName());
        ((TextView)view.findViewById(R.id.event_id)).setText(event.getId());
        ((TextView)view.findViewById(R.id.event_owner)).setText(event.getDescription());

        return view;
    }
}