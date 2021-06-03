package com.ducnguyen46.covstats.view.countryfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ducnguyen46.covstats.MainActivity;
import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.database.DatabaseHelper;
import com.ducnguyen46.covstats.models.CovidCountry;
import com.ducnguyen46.covstats.view.homefragment.CardTopRecyclerAdapter;

import java.util.ArrayList;

public class CountryFragment extends Fragment {

    private SearchView svCountry;
    private RecyclerView rcCountries;
    private ArrayList<CovidCountry> countries;
    private DatabaseHelper databaseHelper;
    private CardTopRecyclerAdapter cardCountryAdapter;

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Country");
        countries = getDataSearchCountry(svCountry.getQuery().toString());
        cardCountryAdapter.setData(countries);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        countries = getDataSearchCountry("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Country");

        svCountry = view.findViewById(R.id.svSearchCountry);
        rcCountries = view.findViewById(R.id.rcCountries);

        cardCountryAdapter = new CardTopRecyclerAdapter(getActivity());
        cardCountryAdapter.setData(countries);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcCountries.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcCountries.setAdapter(cardCountryAdapter);


        //
        svCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                countries = getDataSearchCountry(query);
                cardCountryAdapter.setData(countries);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countries = getDataSearchCountry(newText);
                cardCountryAdapter.setData(countries);
                return false;
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private ArrayList<CovidCountry> getDataSearchCountry(String countryName){
        return databaseHelper.getCountryByName(countryName);
    }
}

