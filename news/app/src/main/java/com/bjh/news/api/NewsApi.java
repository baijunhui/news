package com.bjh.news.api;

import com.bjh.news.Config;
import com.bjh.news.data.NewsResult;
import com.bjh.news.okHttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by baijunhui on 17-3-20.
 */

public class NewsApi {

    private static NewsApi instance;
    private NewsApiService service;

    private NewsApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(NewsApiService.class);
    }

    public static NewsApi instance() {
        if (instance == null) {
            synchronized (NewsApi.class) {
                if (instance == null) {
                    instance = new NewsApi();
                }
            }
        }
        return instance;
    }

    public Observable<NewsResult> getNews(String type, String key) {
        return service.getNews(type, key);
    }
}
