package com.ducnguyen46.covstats.view.homefragment;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.models.CovidCountry;

import java.util.ArrayList;

public class CardTopRecyclerAdapter extends RecyclerView.Adapter<CardTopRecyclerAdapter.CountryTopHolder> {

    private Activity activity;
    private ArrayList<CovidCountry> countries;

    public CardTopRecyclerAdapter(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public CountryTopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.card_top_country, null, false);
        return new CountryTopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryTopHolder holder, int position) {
        CovidCountry country = countries.get(position);
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CountryTopHolder extends RecyclerView.ViewHolder {

        private ImageView imgFlag;
        private TextView tvNameTop;
        private TextView tvTopTotalCase;

        public CountryTopHolder(@NonNull View view) {
            super(view);
            imgFlag = view.findViewById(R.id.imgFlag);
            tvNameTop = view.findViewById(R.id.tvNameTop);
            tvTopTotalCase = view.findViewById(R.id.tvTopTotalCase);
        }
    }
}
