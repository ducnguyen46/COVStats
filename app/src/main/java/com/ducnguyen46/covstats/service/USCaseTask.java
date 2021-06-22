package com.ducnguyen46.covstats.service;

import android.os.AsyncTask;

import com.ducnguyen46.covstats.models.USCovid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class USCaseTask extends AsyncTask<String, Void, ArrayList<USCovid>> {
    @Override
    protected ArrayList<USCovid> doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        URL url;
        ArrayList<USCovid> list = new ArrayList<>();
        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return list;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0)
                return list;

            JSONArray jsonArrayResponse = new JSONArray(buffer.toString());
            for(int i = 0; i < jsonArrayResponse.length(); i++){
                USCovid usCovid = new USCovid(jsonArrayResponse.getJSONObject(i));
                list.add(usCovid);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<USCovid> usCovids) {
        super.onPostExecute(usCovids);
    }
}
