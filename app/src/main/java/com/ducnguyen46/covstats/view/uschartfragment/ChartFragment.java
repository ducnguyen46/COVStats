package com.ducnguyen46.covstats.view.uschartfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ducnguyen46.covstats.MainActivity;
import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.constant.Constant;
import com.ducnguyen46.covstats.models.USCovid;
import com.ducnguyen46.covstats.service.USCaseTask;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ChartFragment extends Fragment {

    private BarChart barChart;
    private USCaseTask usCaseTask;
    @Override
    public void onResume() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("USA Chart");
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("USA Chart");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        barChart = view.findViewById(R.id.barchart);
        usCaseTask = new USCaseTask();

        fillDataChart();
        return view;
    }

    private void fillDataChart(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<USCovid> usCovids = new ArrayList<>(getUSCovidData());
        for (int i = 0; i < 30; i++){
            barEntries.add(new BarEntry(i, usCovids.get(i).getDeath()));
        }
        Collections.reverse(barEntries);
        BarDataSet barDataSet = new BarDataSet(barEntries, "USA Dead Case");
        barDataSet.setColor(R.color.primary_red);
        barDataSet.setValueTextColor(R.color.primary_black);
        barDataSet.setValueTextSize(14f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
    }
    private ArrayList<USCovid> getUSCovidData(){
        ArrayList<USCovid> list = new ArrayList<>();
        usCaseTask.execute(Constant.URL_US);
        try {
            list.addAll(usCaseTask.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }

    private String parseDate(String str){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str);
            String fromDateToString = new SimpleDateFormat("yyyy/MM/dd").format(date);
            return fromDateToString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}