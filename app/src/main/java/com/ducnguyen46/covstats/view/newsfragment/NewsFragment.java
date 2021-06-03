package com.ducnguyen46.covstats.view.newsfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ducnguyen46.covstats.MainActivity;
import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.models.RssFeed;
import com.ducnguyen46.covstats.models.RssItem;
import com.ducnguyen46.covstats.service.ApiService;
import com.ducnguyen46.covstats.service.RssNewsService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private RecyclerView rcNews;
    private ArrayList<RssItem> rssItems;
    private RssNewsService rssNewsService;
    private NewsAdapter adapter;

    @Override
    public void onResume() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("News");
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("News");

        adapter = new NewsAdapter(getContext());
        rssNewsService = ApiService.getRssNewsService();
        rcNews = view.findViewById(R.id.rcNews);
        rcNews.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rcNews.setAdapter(adapter);
        getNews();

        return view;
    }

    private void getNews(){
        rssNewsService.getRssNews().enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                if(response.isSuccessful()){
                    rssItems = response.body().getChannel().getListItem();
                } else {
                    rssItems = new ArrayList<>();
                }
                adapter.setData(rssItems);
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                System.out.println("Lỗi ròi " + t.getMessage());
            }
        });
    }
}