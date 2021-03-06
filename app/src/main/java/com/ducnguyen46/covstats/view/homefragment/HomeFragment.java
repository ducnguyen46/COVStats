 package com.ducnguyen46.covstats.view.homefragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ducnguyen46.covstats.MainActivity;
import com.ducnguyen46.covstats.R;
import static com.ducnguyen46.covstats.constant.Constant.*;

import com.ducnguyen46.covstats.database.DatabaseHelper;
import com.ducnguyen46.covstats.service.CovidDataTask;
import com.ducnguyen46.covstats.models.CovidCountry;
import com.ducnguyen46.covstats.models.InfoCard;
import com.ducnguyen46.covstats.view.CardInfoRecyclerAdapter;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {
    private RecyclerView rcWorldCase;
    private RecyclerView rcTopCountries;
    private ArrayList<InfoCard> infoCards;
    private CardInfoRecyclerAdapter rcWordCaseAdapter;
    private CovidDataTask covidDataTask;

    private ArrayList<CovidCountry> topCountries;
    private CardTopRecyclerAdapter rcCardTopAdapter;
    private CovidCountry globalInfo;


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("COVStats");
        topCountries = getTop5Country();
        rcCardTopAdapter.setData(topCountries);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCovidGlobal();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcWorldCase = view.findViewById(R.id.rvWorldCase);
        rcTopCountries = view.findViewById(R.id.rcTopCountries);
        infoCards = getCovidGlobal();

        rcWordCaseAdapter = new CardInfoRecyclerAdapter(getActivity());
        rcWordCaseAdapter.setData(infoCards);

        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2,
                RecyclerView.VERTICAL, false);
        rcWorldCase.setLayoutManager(gridLayout);
        rcWorldCase.setAdapter(rcWordCaseAdapter);

        topCountries = getTop5Country();
        rcCardTopAdapter = new CardTopRecyclerAdapter(getActivity());
        rcCardTopAdapter.setData(topCountries);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcTopCountries.setLayoutManager(linearLayoutManager);
        rcTopCountries.setAdapter(rcCardTopAdapter);

        return view;
    }

    private ArrayList<InfoCard> getCovidGlobal(){
        ArrayList<InfoCard> globalInfoCards = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        globalInfo = databaseHelper.getGlobalInfo();

        if(globalInfo == null){
            covidDataTask = new CovidDataTask(getActivity());
            covidDataTask.execute(URL_LINK);

            try {
                JSONObject jsonResponse = covidDataTask.get();
                JSONObject jsonGlobal = jsonResponse.getJSONObject("Global");

                globalInfo = new CovidCountry(jsonGlobal);
            } catch (ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }
        }

        InfoCard cardNewCase = new InfoCard(NEW_CASE, globalInfo.getNewConfirmed());
        globalInfoCards.add(cardNewCase);

        InfoCard cardTotalCase = new InfoCard(TOTAL_CASE, globalInfo.getTotalConfirmed());
        globalInfoCards.add(cardTotalCase);

        InfoCard cardNewRecovered = new InfoCard(NEW_RECOVERED, globalInfo.getNewRecovered());
        globalInfoCards.add(cardNewRecovered);

        InfoCard cardTotalRecovered = new InfoCard(TOTAL_RECOVERED, globalInfo.getTotalRecovered());
        globalInfoCards.add(cardTotalRecovered);

        InfoCard cardNewDeath = new InfoCard(NEW_DEATH, globalInfo.getNewDeath());
        globalInfoCards.add(cardNewDeath);

        InfoCard cardTotalDeath = new InfoCard(TOTAL_DEATH, globalInfo.getTotalDeath());
        globalInfoCards.add(cardTotalDeath);

        return globalInfoCards;
    }

    private ArrayList<CovidCountry> getTop5Country(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        return databaseHelper.getTop5Country();
    }
}