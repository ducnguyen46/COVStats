package com.ducnguyen46.covstats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ducnguyen46.covstats.service.CovidDataTask;
import com.ducnguyen46.covstats.models.CovidCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GetDataActivity extends AppCompatActivity {

    private CovidDataTask covidDataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        getCovidCountry();
    }

    private ArrayList<CovidCountry> getCovidCountry(){
        ArrayList<CovidCountry> countries = new ArrayList<>();

        covidDataTask = new CovidDataTask(this);
        covidDataTask.execute("https://api.covid19api.com/summary");
        try {
            JSONObject jsonResult = covidDataTask.get();
            JSONArray jsonContries = jsonResult.getJSONArray("Countries");

            for(int i = 0; i < jsonContries.length(); i++){
                CovidCountry country = new CovidCountry(jsonContries.getJSONObject(i));
                countries.add(country);
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return countries;
    }
}