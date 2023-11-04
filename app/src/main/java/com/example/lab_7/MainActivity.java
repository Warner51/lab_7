package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CST2335 Lab 7");

        new FetchSWData().execute();

    }

    private class FetchSWData extends AsyncTask<Void, Void, ArrayList<String>> implements com.example.lab_7.FetchSWData {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> charactersList = new ArrayList<>();

            try {
                String apiUrl = "https://swapi.dev/api/people/?format=json";

                HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                JSONObject SWObject = new JSONObject(sb.toString());
                JSONArray jArray = SWObject.getJSONArray("results");

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject charInfo = jArray.getJSONObject(i);
                    String name = charInfo.getString("name");
                    String height = charInfo.getString("height");
                    String mass =charInfo.getString("mass");

                    String characterInfo = "Name: " + name + "\nHeight: " + height + "\nMass: " + mass;

                    charactersList.add(characterInfo);
                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return charactersList;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            CharListAdapter adapter = new CharListAdapter(MainActivity.this, result);

            ListView charListView = findViewById(R.id.ListView);
            charListView.setAdapter(adapter);

        }
    }
}