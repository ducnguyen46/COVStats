package com.ducnguyen46.covstats.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ducnguyen46.covstats.R;
import  static com.ducnguyen46.covstats.constant.Constant.*;
import com.ducnguyen46.covstats.models.InfoCard;

import java.util.ArrayList;

public class CardInfoRecyclerAdapter extends RecyclerView.Adapter<CardInfoRecyclerAdapter.InfoHolder> {

    private Activity activity;
    private ArrayList<InfoCard> list;

    public CardInfoRecyclerAdapter(Activity activity) {
        this.activity = activity;
        this.list = list;
    }

    public void setData(ArrayList<InfoCard> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.card_info_covid, null, false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
        InfoCard infoCard = list.get(position);

        switch (infoCard.getInfoType()){
            case NEW_RECOVERED:
            case TOTAL_RECOVERED:
                holder.tvStatsInfo.setTextColor(Color.parseColor("#FF00C48C"));
                break;

            case NEW_DEATH:
            case TOTAL_DEATH:
                holder.tvStatsInfo.setTextColor(Color.parseColor("#FFFF647C"));
                break;

            default:
                holder.tvStatsInfo.setTextColor(Color.parseColor("#FF151522"));
        }

        holder.tvTypeInfo.setText(infoCard.getInfoType());
        holder.tvStatsInfo.setText(String.format("%1$,d", infoCard.getStatsInfo()));
        holder.imgTypeInfo.setImageResource(infoCard.getImgInfoType());
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    class InfoHolder extends RecyclerView.ViewHolder {

        private TextView tvTypeInfo, tvStatsInfo;
        private ImageView imgTypeInfo;

        public InfoHolder(@NonNull View view) {
            super(view);

            tvTypeInfo = view.findViewById(R.id.tvTypeInfo);
            tvStatsInfo = view.findViewById(R.id.tvStatsInfo);
            imgTypeInfo = view.findViewById(R.id.imgTypeInfo);
        }
    }
}
