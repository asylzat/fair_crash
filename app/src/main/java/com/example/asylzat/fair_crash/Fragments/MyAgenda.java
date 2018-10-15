package com.example.asylzat.fair_crash.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asylzat.fair_crash.Company;
import com.example.asylzat.fair_crash.EventAdapter;
import com.example.asylzat.fair_crash.MainActivity;
import com.example.asylzat.fair_crash.R;

import java.util.List;

public class MyAgenda extends Fragment {
    ListView lv;
    List<Company> events;
    TextView totalTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_agenda, container, false);

        events = MainActivity.getEvents();
        lv = (ListView) view.findViewById(R.id.schedule);
        totalTime = view.findViewById(R.id.total_time);

        int total = 0;
        for (Company company : events) {
            total += company.getDuration();
        }

        ArrayAdapter<Company> arrayAdapter = new EventAdapter(
                getActivity(),
                events );


        totalTime.setText(String.valueOf(total));
        lv.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Agenda");
    }

}
