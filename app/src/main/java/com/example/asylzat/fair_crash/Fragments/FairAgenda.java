package com.example.asylzat.fair_crash.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asylzat.fair_crash.Company;
import com.example.asylzat.fair_crash.CompanyAdapter;
import com.example.asylzat.fair_crash.HttpHandler;
import com.example.asylzat.fair_crash.MainActivity;
import com.example.asylzat.fair_crash.Presentation;
import com.example.asylzat.fair_crash.PresentationAdapter;
import com.example.asylzat.fair_crash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FairAgenda extends Fragment {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;
    List<Presentation> presentations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fair_agenda, container, false);

        presentations = MainActivity.getPresentations();
        lv = (ListView) view.findViewById(R.id.presentations);

        ArrayAdapter<Presentation> arrayAdapter = new PresentationAdapter(
                getActivity(), ((MainActivity)getActivity()).getMyAgenda(),
                presentations);

        new GetPresentations().execute();

        lv.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Fair Agenda");
    }

    private class GetPresentations extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("https://www.wikway.de/companies/zwik-lectures-json");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    // Getting JSON Array node
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String title = c.getString("lecture_title");
                        String name = c.getString("company_name");
                        String time = c.getString("time_required_to_visit");

                        Presentation presentation = new Presentation();
                        presentation.setTitle(title);
                        presentation.setTime(time);
                        presentation.setName(name);

                        presentations.add(presentation);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("json","Json parsing error: " + e.getMessage());
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new PresentationAdapter(
                    getActivity(), ((MainActivity)getActivity()).getMyAgenda(),
                    presentations);

            lv.setAdapter(adapter);

        }

    }
}


