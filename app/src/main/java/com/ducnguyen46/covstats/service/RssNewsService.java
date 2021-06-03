package com.ducnguyen46.covstats.service;

import com.ducnguyen46.covstats.models.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssNewsService {
    @GET("suc-khoe.rss")
    Call<RssFeed> getRssNews();
}
