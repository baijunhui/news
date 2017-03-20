package com.bjh.news.api;

import com.bjh.news.data.NewsResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by baijunhui on 17-3-20.
 */

public interface NewsApiService {

    @GET("/toutiao/index")
    Observable<NewsResult> getNews(@Query("type") String type, @Query("key") String key);
}
