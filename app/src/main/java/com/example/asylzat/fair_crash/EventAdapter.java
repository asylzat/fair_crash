package com.example.asylzat.fair_crash;
        ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

        import java.util.List;

public class EventAdapter extends ArrayAdapter<Company> {
    private List<Company> list;
    private Context context;
    private int total = 0;

    TextView currentEventName,
            currentEventTime,
            removeEvent;

    public EventAdapter(Context context, List<Company> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.selected_event_item,parent,false
            );
        }

        final Company currentEvent = getItem(position);

        currentEventName = (TextView)listItemView.findViewById(R.id.selected_event_name);
        currentEventTime = (TextView)listItemView.findViewById(R.id.selected_event_time);
        removeEvent = (TextView)listItemView.findViewById(R.id.delete_item);


        String duration = String.valueOf(currentEvent.getDuration());
        if( duration == null){
            currentEventTime.setText("not specified");
        }else {
            currentEventTime.setText(duration);
        }
        currentEventName.setText(currentEvent.getName());

//        totalTime.setText(getTotal());

        removeEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }
}