package com.ducnguyen46.covstats.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ducnguyen46.covstats.database.DatabaseHelper;
import com.ducnguyen46.covstats.models.CovidCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CovidDataTask extends AsyncTask<String, Integer, JSONObject> {
    private Activity activity;
    private DatabaseHelper databaseHelper;

    public CovidDataTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Syncing data...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        URL url;

        databaseHelper = new DatabaseHelper(activity);
        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0)
                return null;

            JSONObject jsonResponse = new JSONObject(buffer.toString());

            //insert global
            JSONObject jsonGlobal = jsonResponse.getJSONObject("Global");
            CovidCountry globalCovid = new CovidCountry(jsonGlobal);
            globalCovid.setCountry("Global");
            globalCovid.setCountryCode("Global");

            databaseHelper.insertOrUpdateCountryData(globalCovid);
            //insert all country
            JSONArray jsonArrayCountries = jsonResponse.getJSONArray("Countries");
            for(int i = 0; i < jsonArrayCountries.length(); i++){
                JSONObject jsonCountry = jsonArrayCountries.getJSONObject(i);
                CovidCountry country = new CovidCountry(jsonCountry);
                databaseHelper.insertOrUpdateCountryData(country);
            }

            return jsonResponse;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        Toast.makeText(activity, "Downloaded data", Toast.LENGTH_SHORT).show();
    }
}
