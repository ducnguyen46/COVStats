package com.ducnguyen46.covstats.view.newsfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.models.RssItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context context;
    private ArrayList<RssItem> list;
    public void setData(ArrayList<RssItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_news, null, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        RssItem item = list.get(position);
        String imageUrl = item.getImageURL();
        String des = item.getDes();

        Picasso.get().load(imageUrl).into(holder.imgNews);
        holder.titleNewsTv.setText(item.getTitle());
        holder.desNewsTv.setText(des);

        try {
            Date dateFromRss = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse(item.getPubDate());
            String dateStr = new SimpleDateFormat("EEE, dd/MM/yyyy").format(dateFromRss);
            holder.dateNewsTv.setText(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", item.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null? list.size() : 0;
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        private ImageView imgNews ;
        private TextView titleNewsTv, desNewsTv, dateNewsTv;

        public NewsHolder(@NonNull View view) {
            super(view);
            imgNews = view.findViewById(R.id.imgNews);
            titleNewsTv = view.findViewById(R.id.titleNewsTv);
            desNewsTv = view.findViewById(R.id.desNewsTv);
            dateNewsTv = view.findViewById(R.id.dateNews);
        }
    }
}
