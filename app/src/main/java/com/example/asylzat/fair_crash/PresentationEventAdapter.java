package com.example.asylzat.fair_crash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asylzat.fair_crash.Fragments.MyAgenda;

import java.util.List;

public class PresentationEventAdapter extends ArrayAdapter<Presentation>{

        private List<Presentation> list;
        private Context context;
        private int total = 0;

        TextView currentEventName,
                currentEventTime,
                removeEvent;
        MyAgenda myAgenda;

        public PresentationEventAdapter(Context context, MyAgenda myAgenda, List<Presentation> list) {
            super(context, 0, list);
            this.list = list;
            this.myAgenda = myAgenda;
            this.context = context;
        }


        public View getView(final int position, View convertView, ViewGroup parent){
            View listItemView = convertView;
            if(listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.selected_event_item,parent,false
                );
            }

            final Presentation currentEvent = getItem(position);

            currentEventName = (TextView)listItemView.findViewById(R.id.selected_event_name);
            currentEventTime = (TextView)listItemView.findViewById(R.id.selected_event_time);
            removeEvent = (TextView)listItemView.findViewById(R.id.delete_item);


            String duration = String.valueOf(currentEvent.getTime());
            if( duration == null){
                currentEventTime.setText("not specified");
            }else {
                currentEventTime.setText(duration);
            }
            currentEventName.setText(currentEvent.getTitle());


            removeEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    myAgenda.calculateTotal();
                    notifyDataSetChanged();
                    Toast toast = Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            return listItemView;
        }
    }

