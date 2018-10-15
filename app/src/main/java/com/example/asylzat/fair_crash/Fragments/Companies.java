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
import com.example.asylzat.fair_crash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Companies extends Fragment  {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;
    List<Company> companies;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.companies, container, false);

        companies = MainActivity.getCompanies();
        lv = (ListView) view.findViewById(R.id.companies);

        ArrayAdapter<Company> arrayAdapter = new CompanyAdapter(
                getActivity(),
                companies );

        new GetCompanies().execute();

        lv.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Companies");
    }

    private class GetCompanies extends AsyncTask<Void, Void, Void> {
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
            String jsonStr = sh.makeServiceCall("https://www.wikway.de/companies/zwik-exhibitors-json");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    // Getting JSON Array node
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String name = c.getString("name");
                        String info = c.getString("description");
                        String logo = c.getString("logo");
                        String link = c.getString("link");
                        String time = c.getString("time_required_to_visit");

                        Company company = new Company();
                        company.setName(name);
                        company.setInfo(info);
                        company.setLogo(logo);
                        company.setDuration(time);
                        company.setLink(link);

                        companies.add(company);
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
            ListAdapter adapter = new CompanyAdapter(
                    getActivity(),
                    companies);

            lv.setAdapter(adapter);

        }

    }
}


