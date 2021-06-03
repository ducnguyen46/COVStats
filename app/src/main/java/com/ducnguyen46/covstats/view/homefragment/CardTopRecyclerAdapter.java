package com.ducnguyen46.covstats.view.homefragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.models.CovidCountry;
import static com.ducnguyen46.covstats.constant.Constant.*;
import com.ducnguyen46.covstats.service.GetFlagImageAPI;
import com.ducnguyen46.covstats.view.countryfragment.DetailCountryActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardTopRecyclerAdapter extends RecyclerView.Adapter<CardTopRecyclerAdapter.CountryTopHolder> {

    private Activity activity;
    private ArrayList<CovidCountry> countries;

    public CardTopRecyclerAdapter(Activity activity){
        this.activity = activity;
    }

    public void setData(ArrayList<CovidCountry> list){
        this.countries = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryTopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.card_top_country, null, false);
        return new CountryTopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryTopHolder holder, int position) {
        // api lấy ảnh cờ của một nước
        // https://flagcdn.com/w160/xx.png
        // xx là country code

        CovidCountry country = countries.get(position);
//        holder.tvTopTotalCase.setText(String.valueOf(country.getTotalConfirmed()));
        holder.tvTopTotalCase.setText(String.format("%1$,d", country.getTotalConfirmed()));
        holder.tvNameTop.setText(country.getCountry());
        Picasso.get()
                .load("https://flagcdn.com/w160/" + country.getCountryCode().toLowerCase() +".png")
                .into(holder.imgFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailCountryActivity.class);
                intent.putExtra("country", country);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
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
