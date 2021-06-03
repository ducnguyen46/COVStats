package com.ducnguyen46.covstats.service;

import com.ducnguyen46.covstats.constant.Constant;

public class ApiService {
    public static RssNewsService getRssNewsService(){
        return RetrofitClient.getClient(Constant.URL_NEWS).create(RssNewsService.class);
    }
}
