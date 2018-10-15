package com.example.asylzat.fair_crash.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asylzat.fair_crash.Company;
import com.example.asylzat.fair_crash.EventAdapter;
import com.example.asylzat.fair_crash.MainActivity;
import com.example.asylzat.fair_crash.Presentation;
import com.example.asylzat.fair_crash.PresentationAdapter;
import com.example.asylzat.fair_crash.PresentationEventAdapter;
import com.example.asylzat.fair_crash.R;

import java.util.List;

public class MyAgenda extends Fragment {
    ListView lv;
    ListView listView;
    List<Company> events;
    List<Presentation> presentationEvents;
    TextView totalTime;
    int total = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_agenda, container, false);

        events = MainActivity.getEvents();
        lv = (ListView) view.findViewById(R.id.schedule);

        presentationEvents =MainActivity.getPresentationEvents();
        listView = (ListView)view.findViewById(R.id.schedule2);

        totalTime= view.findViewById(R.id.total_time);

        calculateTotal();

        ArrayAdapter<Company> arrayAdapter = new EventAdapter(
                getActivity(), this,
                events );

        ArrayAdapter<Presentation> arrayAdapter1 = new PresentationEventAdapter(
                getActivity(), this,
                presentationEvents);


        lv.setAdapter(arrayAdapter);
        listView.setAdapter(arrayAdapter1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Agenda");
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public void calculateTotal(){
        total = 0;
        for (Company company : MainActivity.getEvents()) {
            total += company.getDuration();
        }
        for (Presentation presentation: MainActivity.getPresentationEvents()) {
            total += presentation.getTime();
        }

        if (totalTime != null) {
            totalTime.setText(String.valueOf(total));

        }

    }


}
